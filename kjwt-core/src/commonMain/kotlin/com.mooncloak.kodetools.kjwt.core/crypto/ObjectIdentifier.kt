package com.mooncloak.kodetools.kjwt.core.crypto

import kotlin.jvm.JvmInline

/**
 * Represents an ASN.1 Object Identifier value.
 */
@JvmInline
internal value class ObjectIdentifier private constructor(
    val value: IntArray
) {

    override fun toString(): String =
        value.joinToString(separator = ".")

    /**
     * Returns a [ByteArray] of the ASN.1 DER encoded representation of this
     * [ObjectIdentifier.value].
     */
    internal fun encode(): ByteArray {
        val result = mutableListOf(TAG)

        val encodedValue = encodeValue()
        val encodedSize = encodedValue.size.toDerEncodedSize()

        result.addAll(encodedSize)
        result.addAll(encodedValue)

        return result.toByteArray()
    }

    private fun encodeValue(): List<Byte> {
        val encoded = mutableListOf<Byte>()

        // Encode first two integer values.
        // The first two values are combined into one byte. They are guaranteed to be a single byte
        // in size according to the specification.
        val firstByte = ((40 * value[0]) + value[1]).toByte()
        encoded.add(firstByte)

        // Encode the remaining integer values.
        // According to the specification, these values are encoded as follows:
        // - If the value is less than 128 (max byte value), then it is encoded as a single byte
        // - If the value is greater than 128, then it uses "variable size encoding":
        //     * Break the Integer down into bytes
        //     * The most significant bit must be a value of 1 for each byte except for the last
        //       byte which must be 0.
        //     * After the most significant bit, encode the next 7 bits of the binary
        //       representation of the number.
        for (i in 2 until value.size) {
            encoded.addAll(value[i].toDerEncoded())
        }

        return encoded
    }

    internal companion object {

        internal const val TAG: Byte = 0x06

        internal fun of(value: String): ObjectIdentifier {
            val array = value.split('.')
                .map { it.toInt() }
                .toIntArray()

            return ObjectIdentifier(value = array)
        }

        internal fun of(value: IntArray): ObjectIdentifier =
            ObjectIdentifier(value = value)

        internal fun of(value: Collection<Int>): ObjectIdentifier =
            ObjectIdentifier(value = value.toIntArray())

        internal fun of(vararg value: Int): ObjectIdentifier =
            ObjectIdentifier(value = intArrayOf(*value))

        internal fun decode(value: ByteArray): ObjectIdentifier {
            require(value[0] == TAG) {
                "Not a valid binary representation of an Object Identifier (OID). The first byte must be the value '$TAG'."
            }

            val result = mutableListOf<Int>()

            val sizeAndValueList = value.toList()
                .subList(
                    fromIndex = 1,
                    toIndex = value.size
                )

            val size = sizeAndValueList.toDerDecodedSize()

            val valueList = sizeAndValueList.subList(
                fromIndex = size.byteSize,
                toIndex = sizeAndValueList.size
            )

            // Decode the first two values
            val firstByte = valueList[0].toInt() and 0xFF
            result.add(firstByte / 40)
            result.add(firstByte % 40)

            // Decode the remaining bytes
            var index = 1
            while (index < valueList.size) {
                val v = valueList.toDerDecoded(startIndex = index)

                result.add(v.value)

                index += v.byteSize
            }

            return of(value = result)
        }
    }
}

private data class ByteSizeAndValue(
    val byteSize: Int,
    val value: Int
)

private fun Int.toDerEncodedSize(): List<Byte> {
    val encoded = mutableListOf<Byte>()

    if (this < 128) {
        encoded.add(this.toByte())
    } else {
        val sizeBytes = mutableListOf<Byte>()
        var remaining = this
        while (remaining > 0) {
            // Add just the least significant 8 bits
            sizeBytes.add((remaining and 0xFF).toByte())

            // Bit shift right 8 bits to get the next bytes to add
            remaining = remaining shr 8
        }

        // The lower 7 bits of the first number indicate the number of subsequent bytes that
        // contain the actual length value
        encoded.add((0x80 or sizeBytes.size).toByte())

        // TODO: Probably can update the loop above to shift left and place the bytes in the
        //  correct order the first time around, instead of having to reverse it here.
        encoded.addAll(sizeBytes.reversed())
    }

    return encoded
}

private fun List<Byte>.toDerDecodedSize(): ByteSizeAndValue {
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

private fun Int.toDerEncoded(): List<Byte> {
    // According to the specification, these values are encoded as follows:
    // - If the value is less than 128 (max byte value), then it is encoded as a single byte
    // - If the value is greater than 128, then it uses "variable size encoding":
    //     * Break the Integer down into bytes
    //     * The most significant bit must be a value of 1 for each byte except for the last
    //       byte which must be 0.
    //     * After the most significant bit, encode the next 7 bits of the binary
    //       representation of the number.

    val encoded = mutableListOf<Byte>()

    var v = this

    // While the value is greater than 128 (0x80)
    while (v >= 0x80) {
        // Take the lower 7 bits of the byte, and set the most significant bit (MSB) to a
        // binary value of 1.
        val nextEncodedByte = (v and 0x7F or 0x80).toByte()

        encoded.add(nextEncodedByte)

        // Shift right 7 bits so that we can take the next bit values
        v = v shr 7
    }

    // Add the last value without the most significant bit set to 1. This value is
    // guaranteed to be less than 128.
    encoded.add(v.toByte())

    return encoded
}

// The inverse of the Int.toDerEncoded() function.
private fun List<Byte>.toDerDecoded(startIndex: Int = 0): ByteSizeAndValue {
    var value = 0
    var index = startIndex
    var byte = this[index].toInt() and 0xFF

    while ((byte and 0x80) != 0) {
        value = (value shl 7) or (byte and 0x7F)
        index++
        byte = this[index].toInt() and 0xFF
    }

    value = (value shl 7) or (byte and 0x7F)

    return ByteSizeAndValue(
        byteSize = index - startIndex + 1,
        value = value
    )
}
