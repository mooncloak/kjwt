package com.mooncloak.kodetools.kjwt.core.crypto

internal const val SEQUENCE_TAG = 0x30
internal val NULL_PARAMETERS = byteArrayOf(0x05, 0x00)

internal data class ByteSizeAndValue internal constructor(
    val byteSize: Int,
    val value: Int
)

internal fun Int.toDerEncodedSize(): List<Byte> {
    val encoded = mutableListOf<Byte>()

    if (this < 128) {
        encoded.add(this.toByte())
    } else {
        val sizeBytes = mutableListOf<Byte>()
        var remaining = this
        while (remaining > 0) {
            // Add just the least significant 8 bits
            sizeBytes.add(0, (remaining and 0xFF).toByte())

            // Bit shift right 8 bits to get the next bytes to add
            remaining = remaining shr 8
        }

        // The lower 7 bits of the first number indicate the number of subsequent bytes that
        // contain the actual length value
        encoded.add(sizeBytes.size.toByte())

        // TODO: Probably can update the loop above to shift left and place the bytes in the
        //  correct order the first time around, instead of having to reverse it here.
        encoded.addAll(sizeBytes)
    }

    return encoded
}

internal fun List<Byte>.toDerDecodedSize(): ByteSizeAndValue {
    require(this.isNotEmpty()) { "Collection of bytes representing size cannot be empty." }

    val firstByte = this[0].toInt() and 0xFF

    if (firstByte < 0x80) {
        return ByteSizeAndValue(
            byteSize = 1,
            value = firstByte
        )
    } else {
        val byteSize = firstByte and 0x7F

        require(byteSize + 1 < this.size) { "Collection of bytes representing size is missing values." }

        var length = 0
        for (i in 0 until byteSize) {
            length = (length shl 8) or (this[i + 1].toInt() and 0xFF)
        }

        return ByteSizeAndValue(
            byteSize = byteSize,
            value = length
        )
    }
}
