package com.mooncloak.kodetools.aes

import kotlin.jvm.JvmInline

/**
 * Represents the State object defined by the [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf#page=6).
 *
 * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf#page=6)
 */
@JvmInline
internal value class State private constructor(
    // We make this value private to control the mutations of the underlying ByteArray within this class via the set function.
    private val value: ByteArray = ByteArray(size = 16)
) {

    init {
        require(value.size == 16) {
            "The State, defined by the AES Specification, MUST have a size of 16. Since it is represented as a two-dimensional 4x4 array of bytes."
        }
    }

    internal inline operator fun get(rowIndex: Int, columnIndex: Int): Byte {
        require(rowIndex in 0 until 4) {
            "`rowIndex` property MUST be greater than or equal to 0 and less than 4."
        }
        require(columnIndex in 0 until 4) {
            "`columnIndex` property MUST be greater than or equal to 0 and less than 4."
        }

        // See section 3.4 of the specification
        return this.value[rowIndex + 4 * columnIndex]
    }

    internal inline operator fun set(rowIndex: Int, columnIndex: Int, value: Byte) {
        require(rowIndex in 0 until 4) {
            "`rowIndex` property MUST be greater than or equal to 0 and less than 4."
        }
        require(columnIndex in 0 until 4) {
            "`columnIndex` property MUST be greater than or equal to 0 and less than 4."
        }

        // See section 3.4 of the specification
        this.value[rowIndex + 4 * columnIndex] = value
    }

    internal inline fun output(): ByteArray {
        val output = ByteArray(size = 16)

        for (r in 0 until 4) {
            for (c in 0 until 4) {
                output[r + 4 * c] = this[r, c]
            }
        }

        return output
    }

    internal companion object {

        internal inline operator fun invoke(input: ByteArray): State {
            require(input.size == 16) {
                "The State, defined by the AES Specification, MUST have a size of 16. Since it is represented as a two-dimensional 4x4 array of bytes."
            }

            val state = State()

            for (r in 0 until 4) {
                for (c in 0 until 4) {
                    state[r, c] = input[r + 4 * c]
                }
            }

            return state
        }
    }
}

/**
 * Performs the ShiftRows function defined by Section 5.1.2 of the
 * [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf#page=14).
 */
internal inline fun State.shiftRows(): State {
    // The first row is kept unchanged.
    for (r in 1 until 4) {
        for (c in 0 until 4) {
            this[r, c] = this[r, (c + r) % 4]
        }
    }

    return this
}

internal inline fun State.mixColumns(): State {
    val temp = Array(4) { ByteArray(4) }

    for (i in 0..3) {
        for (j in 0..3) {
            temp[i][j] = (
                    0x02.multiplyGF256(this[0, j].toInt()) xor
                            0x03.multiplyGF256(this[1, j].toInt()) xor
                            this[2, j].toInt() xor
                            this[3, j].toInt()
                    ).toByte()
        }
    }

    for (i in 0..3) {
        for (j in 0..3) {
            this[i, j] = (
                    this[i, j].toInt() xor
                            0x02.multiplyGF256(temp[i][j].toInt()) xor
                            0x03.multiplyGF256(temp[(i + 1) % 4][j].toInt()) xor
                            temp[(i + 2) % 4][j].toInt() xor
                            temp[(i + 3) % 4][j].toInt()
                    ).toByte()
        }
    }

    return this
}

internal inline fun State.addRoundKey(roundKey: Array<ByteArray>): State {
    for (r in 0 until 4) {
        for (c in 0 until 4) {
            this[r, c] = (this[r, c].toInt() xor roundKey[r][c].toInt()).toByte()
        }
    }

    return this
}

@OptIn(ExperimentalUnsignedTypes::class)
internal inline fun State.subBytes(): State {
    for (r in 0..3) {
        for (c in 0..3) {
            val x = this[r, c].toInt() and 0x0F
            val y = this[r, c].toInt() shr 4

            this[r, c] = sbox[y][x].toByte()
        }
    }

    return this
}

internal inline fun State.invShiftRows(): State {
    for (r in 1..3) {
        val temp = ByteArray(4)

        for (c in 0..3) {
            temp[c] = this[r, (c - r + 4) % 4]
        }

        for (c in 0..3) {
            this[r, c] = temp[c]
        }
    }

    return this
}

@OptIn(ExperimentalUnsignedTypes::class)
internal inline fun State.invSubBytes(): State {
    for (r in 0..3) {
        for (c in 0..3) {
            this[r, c] = invSbox[this[r, c].toInt() ushr 4][this[r, c].toInt() and 0x0f].toByte()
        }
    }

    return this
}

internal inline fun State.invMixColumns(): State {
    for (c in 0..3) {
        val a = IntArray(4)
        val b = IntArray(4)

        for (i in 0..3) {
            a[i] = this[i, c].toInt() and 0xff
        }

        //@formatter:off
        b[0] = a[0].multiplyGF256(0x0e) xor a[1].multiplyGF256(0x0b) xor a[2].multiplyGF256(0x0d) xor a[3].multiplyGF256(0x09)
        b[1] = a[0].multiplyGF256(0x09) xor a[1].multiplyGF256(0x0e) xor a[2].multiplyGF256( 0x0b) xor a[3].multiplyGF256( 0x0d)
        b[2] = a[0].multiplyGF256(0x0d) xor a[1].multiplyGF256( 0x09) xor a[2].multiplyGF256( 0x0e) xor a[3].multiplyGF256( 0x0b)
        b[3] = a[0].multiplyGF256( 0x0b) xor a[1].multiplyGF256( 0x0d) xor a[2].multiplyGF256( 0x09) xor a[3].multiplyGF256(0x0e)
        //@formatter:on

        for (i in 0..3) {
            this[i, c] = b[i].toByte()
        }
    }

    return this
}
