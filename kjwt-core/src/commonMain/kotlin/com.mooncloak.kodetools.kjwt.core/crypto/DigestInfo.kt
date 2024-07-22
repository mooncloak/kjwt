package com.mooncloak.kodetools.kjwt.core.crypto

internal class DigestInfo internal constructor(
    val digestAlgorithm: AlgorithmIdentifier,
    val digest: ByteArray
)

internal fun DigestInfo.encode(): ByteArray {
    val result = mutableListOf<Byte>()

    result.add(SEQUENCE_TAG.toByte())

    // Each of the algorithms have NULL parameters which is represented by the bytes 0x05 and 0x00
    val encoded = (this.digestAlgorithm.encode() + digest).toList()
    val encodedSize = encoded.size.toDerEncodedSize()

    result.addAll(encodedSize)
    result.addAll(encoded)

    return result.toByteArray()
}
