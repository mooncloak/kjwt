package com.mooncloak.kodetools.kjwt.core.crypto

import kotlin.coroutines.cancellation.CancellationException
import kotlin.jvm.JvmInline

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
    fun encode(
        @Suppress("LocalVariableName") M: ByteArray,
        emLen: Int,
        hash: HashFunction,
        hLen: Int,
        hashAlgorithm: DigestAlgorithm
    ) {
        @Suppress("LocalVariableName")
        val H = hash.hash(M)

        if (H.size > hLen) {
            throw MessageTooLongException(
                expectedLength = hLen,
                actualLength = H.size
            )
        }

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

/**
 * Represents a hash algorithm used for [EmsaPkcs1V15Encoding].
 *
 * @see [RFC-8017 Appendix A.2.4](https://www.rfc-editor.org/rfc/rfc8017#appendix-A.2.4)
 * @see [RFC-8017 Appendix B](https://www.rfc-editor.org/rfc/rfc8017#appendix-B)
 */
internal enum class DigestAlgorithm(
    internal val oid: ObjectIdentifier
) {

    MD2(oid = ObjectIdentifier.of(1, 2, 840, 113549, 2, 2)),

    MD5(oid = ObjectIdentifier.of(1, 2, 840, 113549, 2, 5)),

    SHA1(oid = ObjectIdentifier.of(1, 3, 14, 3, 2, 26)),

    SHA224(oid = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 4)),

    SHA256(oid = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 1)),

    SHA384(oid = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 2)),

    SHA512(oid = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 3)),

    SHA512_224(oid = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 5)),

    SHA512_256(oid = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 6))
}
