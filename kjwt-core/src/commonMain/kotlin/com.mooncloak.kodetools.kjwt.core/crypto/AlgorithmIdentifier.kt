package com.mooncloak.kodetools.kjwt.core.crypto

/**
 * Represents a hash algorithm used for [EmsaPkcs1V15Encoding].
 *
 * @see [RFC-8017 Appendix A.2.4](https://www.rfc-editor.org/rfc/rfc8017#appendix-A.2.4)
 * @see [RFC-8017 Appendix B](https://www.rfc-editor.org/rfc/rfc8017#appendix-B)
 */
internal enum class AlgorithmIdentifier(
    internal val algorithm: ObjectIdentifier
) {

    MD2(algorithm = ObjectIdentifier.of(1, 2, 840, 113549, 2, 2)),

    MD5(algorithm = ObjectIdentifier.of(1, 2, 840, 113549, 2, 5)),

    SHA1(algorithm = ObjectIdentifier.of(1, 3, 14, 3, 2, 26)),

    SHA224(algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 4)),

    SHA256(algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 1)),

    SHA384(algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 2)),

    SHA512(algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 3)),

    SHA512_224(algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 5)),

    SHA512_256(algorithm = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 6))
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
