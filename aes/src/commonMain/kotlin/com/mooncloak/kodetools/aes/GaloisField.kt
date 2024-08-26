package com.mooncloak.kodetools.aes

import kotlin.experimental.xor

internal infix fun Byte.plusGF256(b: Byte): Byte = this xor b

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
