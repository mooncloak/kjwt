package com.mooncloak.kodetools.kjwt.core.crypto

import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

/**
 * Represents a hash algorithm used for [EmsaPkcs1V15Encoding].
 *
 * @property [algorithm] The [ObjectIdentifier] of the particular algorithm that is represented.
 *
 * @property [hLen] The byte output length of the hash algorithm.
 *
 * @see [RFC-8017 Appendix A.2.4](https://www.rfc-editor.org/rfc/rfc8017#appendix-A.2.4)
 * @see [RFC-8017 Appendix B](https://www.rfc-editor.org/rfc/rfc8017#appendix-B)
 */
internal enum class AlgorithmIdentifier(
    internal val algorithm: ObjectIdentifier,
    internal val hLen: Int
) {

    MD2(
        algorithm = ObjectIdentifier.of(1, 2, 840, 113549, 2, 2),
        hLen = 16
    ),

    MD5(
        algorithm = ObjectIdentifier.of(1, 2, 840, 113549, 2, 5),
        hLen = 16
    ),

    SHA1(
        algorithm = ObjectIdentifier.of(1, 3, 14, 3, 2, 26),
        hLen = 20
    ),

    SHA224(
        algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 4),
        hLen = 28
    ),

    SHA256(
        algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 1),
        hLen = 32
    ),

    SHA384(
        algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 2),
        hLen = 48
    ),

    SHA512(
        algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 3),
        hLen = 64
    ),

    SHA512_224(
        algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 5),
        hLen = 28
    ),

    SHA512_256(
        algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 6),
        hLen = 32
    )
}

/**
 * Retrieves a [HashFunction] for this [AlgorithmIdentifier].
 */
internal val AlgorithmIdentifier.hashFunction: HashFunction
    get() = when (this) {
        AlgorithmIdentifier.MD2 -> error("Unsupported hash function algorithm 'MD2'.")
        AlgorithmIdentifier.MD5 -> HashFunction.MD5
        AlgorithmIdentifier.SHA1 -> HashFunction.Sha1
        AlgorithmIdentifier.SHA224 -> HashFunction.Sha224
        AlgorithmIdentifier.SHA256 -> HashFunction.Sha256
        AlgorithmIdentifier.SHA384 -> HashFunction.Sha384
        AlgorithmIdentifier.SHA512 -> HashFunction.Sha512
        AlgorithmIdentifier.SHA512_224 -> HashFunction.Sha512_224
        AlgorithmIdentifier.SHA512_256 -> HashFunction.Sha512_256
    }

@ExperimentalJwtApi
internal val SignatureAlgorithm.algorithmIdentifier: AlgorithmIdentifier
    get() = when (this) {
        SignatureAlgorithm.RS256 -> AlgorithmIdentifier.SHA256
        SignatureAlgorithm.RS384 -> AlgorithmIdentifier.SHA384
        SignatureAlgorithm.RS512 -> AlgorithmIdentifier.SHA512
        else -> throw UnsupportedJwtSignatureAlgorithm(
            message = "Signature algorithm '${this.serialName}' is not supported by this component."
        )
    }

internal fun AlgorithmIdentifier.encode(): ByteArray {
    val result = mutableListOf<Byte>()

    result.add(SEQUENCE_TAG.toByte())

    // Each of the algorithms have NULL parameters which is represented by the bytes 0x05 and 0x00
    val encodedAlgorithm = (this.algorithm.encode() + NULL_PARAMETERS).toList()
    val encodedSize = encodedAlgorithm.size.toDerEncodedSize()

    result.addAll(encodedSize)
    result.addAll(encodedAlgorithm)

    return result.toByteArray()
}
