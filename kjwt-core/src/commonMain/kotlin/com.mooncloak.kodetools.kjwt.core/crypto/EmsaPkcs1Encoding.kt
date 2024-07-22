package com.mooncloak.kodetools.kjwt.core.crypto

import kotlin.coroutines.cancellation.CancellationException

/**
 * A component that performs the EMSA-PKCS1-v1_5-ENCODE encoding function defined by
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-9.2) and used for RSA signature
 * generation.
 *
 * @see [encode] for the encoding function.
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-9.2)
 */
internal data object EmsaPkcs1V15Encoding {

    /**
     * Performs the EMSA-PKCS1-v1_5-ENCODE encoding function defined by
     * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-9.2) and used for RSA signature
     * generation.
     *
     * > [!Note]
     * > In the specification, you will often see the terms "octet" and "octet string". These terms
     * > do not appear to be defined in the specification itself, but they mean the following:
     * >
     * > * octet = byte (8-bits)
     * > * octet string = sequence of bytes
     * >
     * > For more info on the terms, see the following Q/A:
     * > https://stackoverflow.com/questions/1241223/octet-string-what-is-it
     *
     * @param [M] The message to be encoded.
     *
     * @param [emLen] The intended length in octets of the encoded message, at least `tLen` + 11,
     * where `tLen` is the octet length of the Distinguished Encoding Rules (DER) encoding T of a
     * certain value computed during the encoding operation.
     *
     * @param [hash] The [HashFunction] to use in the encoding.
     *
     * @param [hLen] The length in octets of the hash function output.
     *
     * @param [hashAlgorithm] The unique hash algorithm identifier defined by the specification,
     * see [Appendix A.2.4](https://www.rfc-editor.org/rfc/rfc8017#appendix-A.2.4).
     *
     * @throws [MessageTooLongException] if the provided message [M] was too long to be encoded.
     *
     * @throws [EncodedMessageLengthTooShortException] if the encoded message's length was too
     * short.
     *
     * @return The encoded message, an octet string of length [emLen].
     *
     * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-9.2)
     */
    @Throws(
        MessageTooLongException::class,
        EncodedMessageLengthTooShortException::class,
        CancellationException::class
    )
    @Suppress("LocalVariableName")
    fun encode(
        @Suppress("LocalVariableName") M: ByteArray,
        emLen: Int,
        hash: HashFunction,
        hLen: Int,
        hashAlgorithm: AlgorithmIdentifier
    ): ByteArray {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2

        // Step 1
        val H = hash.hash(M)

        if (H.size > hLen) {
            throw MessageTooLongException(
                expectedLength = hLen,
                actualLength = H.size
            )
        }

        // Step 2
        val T = DigestInfo(
            digestAlgorithm = hashAlgorithm,
            digest = H
        ).encode()
        val tLen = T.size

        // Step 3
        if (emLen < tLen + 11) {
            throw EncodedMessageLengthTooShortException(
                expectedLength = tLen + 11,
                actualLength = emLen
            )
        }

        // Step 4
        val PS = ByteArray(emLen - tLen - 3) { 0xFF.toByte() }

        // Step 5
        val EM = byteArrayOf(0x00, 0x01) + PS + byteArrayOf(0x00) + T

        // Step 6
        return EM
    }
}

/**
 * Represents an exception that can be thrown from the [EmsaPkcs1V15Encoding.encode] function.
 */
internal sealed class EmsaPkcs1V15EncodingException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(
    message,
    cause
)

/**
 * An [EmsaPkcs1V15EncodingException] representing that a provided message was too long to be
 * encoded.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-9.2)
 */
internal class MessageTooLongException internal constructor(
    internal val expectedLength: Int,
    internal val actualLength: Int
) : EmsaPkcs1V15EncodingException(
    message = "message too long"
)

/**
 * An [EmsaPkcs1V15EncodingException] representing that an encoded message was too short.
 *
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-9.2)
 */
internal class EncodedMessageLengthTooShortException internal constructor(
    internal val expectedLength: Int,
    internal val actualLength: Int
) : EmsaPkcs1V15EncodingException(
    message = "intended encoded message length too short"
)

