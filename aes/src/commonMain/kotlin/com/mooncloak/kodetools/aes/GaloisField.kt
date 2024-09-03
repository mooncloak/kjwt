package com.mooncloak.kodetools.aes

import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.experimental.xor

internal inline infix fun Byte.plusGF256(b: Byte): Byte = this xor b

internal infix fun Int.multiplyGF256(b: Int): Int {
    var p = 0
    var aa = this
    var bb = b
    var hiBitSet: Boolean

    for (counter in 0..7) {
        if (bb and 1 != 0) {
            p = p xor aa
        }

        hiBitSet = aa and 0x80 != 0
        aa = aa shl 1

        if (hiBitSet) {
            aa = aa xor 0x1b // 0x11b
        }

        bb = bb shr 1
    }

    return p
}

internal infix fun ByteArray.multiplyByH(h: ByteArray): ByteArray {
    var result = ByteArray(16)
    var h1 = h

    for (i in 127 downTo 0) {
        if (this[i / 8] and (1 shl (7 - i % 8)).toByte() != 0.toByte()) {
            result = result xor h1
        }

        val isCarry = h1[15] and 0x01 != 0.toByte()

        h1 = h1.shiftRight()

        if (isCarry) {
            h1[0] = h1[0] xor 0xe1.toByte()
        }
    }

    return result
}

private fun ByteArray.shiftRight(): ByteArray {
    val result = this

    for (i in this.size - 1 downTo 1) {
        result[i] = (result[i].toInt() shr 1).toByte() or (result[i - 1].toInt() shl 7).toByte()
    }

    result[0] = (result[0].toInt() shr 1).toByte()

    return result
}
