package com.mooncloak.kodetools.aes

import kotlin.experimental.xor

internal infix fun ByteArray.xor(other: ByteArray): ByteArray {
    require(this.size == other.size) {
        "Byte arrays must have the same size to perform xor on their values."
    }

    return ByteArray(this.size) { i ->
        this[i] xor other[i]
    }
}
