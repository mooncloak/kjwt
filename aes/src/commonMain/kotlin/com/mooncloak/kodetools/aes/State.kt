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
internal fun State.subBytes(): State {
    for (r in 0..3) {
        for (c in 0..3) {
            val x = this[r, c].toInt() and 0x0F
            val y = this[r, c].toInt() shr 4

            this[r, c] = sbox[y][x].toByte()
        }
    }

    return this
}

//@formatter:off
@OptIn(ExperimentalUnsignedTypes::class)
private val sbox = arrayOf(
    ubyteArrayOf(0x63u, 0x7cu, 0x77u, 0x7bu, 0xf2u, 0x6bu, 0x6fu, 0xc5u, 0x30u, 0x01u, 0x67u, 0x2bu, 0xfeu, 0xd7u, 0xabu, 0x76u),
    ubyteArrayOf(0xcau, 0x82u, 0xc9u, 0x7du, 0xfau, 0x59u, 0x47u, 0xf0u, 0xadu, 0xd4u, 0xa2u, 0xafu, 0x9cu, 0xa4u, 0x72u, 0xc0u),
    ubyteArrayOf(0xb7u, 0xfdu, 0x93u, 0x26u, 0x36u, 0x3fu, 0xf7u, 0xccu, 0x34u, 0xa5u, 0xe5u, 0xf1u, 0x71u, 0xd8u, 0x31u, 0x15u),
    ubyteArrayOf(0x04u, 0xc7u, 0x23u, 0xc3u, 0x18u, 0x96u, 0x05u, 0x9au, 0x07u, 0x12u, 0x80u, 0xe2u, 0xebu, 0x27u, 0xb2u, 0x75u),
    ubyteArrayOf(0x09u, 0x83u, 0x2cu, 0x1au, 0x1bu, 0x6eu, 0x5au, 0xa0u, 0x52u, 0x3bu, 0xd6u, 0xb3u, 0x29u, 0xe3u, 0x2fu, 0x84u),
    ubyteArrayOf(0x53u, 0xd1u, 0x00u, 0xedu, 0x20u, 0xfcu, 0xb1u, 0x5bu, 0x6au, 0xcbu, 0xbeu, 0x39u, 0x4au, 0x4cu, 0x58u, 0xcfu),
    ubyteArrayOf(0xd0u, 0xefu, 0xaau, 0xfbu, 0x43u, 0x4du, 0x33u, 0x85u, 0x45u, 0xf9u, 0x02u, 0x7fu, 0x50u, 0x3cu, 0x9fu, 0xa8u),
    ubyteArrayOf(0x51u, 0xa3u, 0x40u, 0x8fu, 0x92u, 0x9du, 0x38u, 0xf5u, 0xbcu, 0xb6u, 0xdau, 0x21u, 0x10u, 0xffu, 0xf3u, 0xd2u),
    ubyteArrayOf(0xcdu, 0x0cu, 0x13u, 0xecu, 0x5fu, 0x97u, 0x44u, 0x17u, 0xc4u, 0xa7u, 0x7eu, 0x3du, 0x64u, 0x5du, 0x19u, 0x73u),
    ubyteArrayOf(0x60u, 0x81u, 0x4fu, 0xdcu, 0x22u, 0x2au, 0x90u, 0x88u, 0x46u, 0xeeu, 0xb8u, 0x14u, 0xdeu, 0x5eu, 0x0bu, 0xdbu),
    ubyteArrayOf(0xe0u, 0x32u, 0x3au, 0x0au, 0x49u, 0x06u, 0x24u, 0x5cu, 0xc2u, 0xd3u, 0xacu, 0x62u, 0x91u, 0x95u, 0xe4u, 0x79u),
    ubyteArrayOf(0xe7u, 0xc8u, 0x37u, 0x6du, 0x8du, 0xd5u, 0x4eu, 0xa9u, 0x6cu, 0x56u, 0xf4u, 0xeau, 0x65u, 0x7au, 0xaeu, 0x08u),
    ubyteArrayOf(0xbau, 0x78u, 0x25u, 0x2eu, 0x1cu, 0xa6u, 0xb4u, 0xc6u, 0xe8u, 0xddu, 0x74u, 0x1fu, 0x4bu, 0xbdu, 0x8bu, 0x8au),
    ubyteArrayOf(0x70u, 0x3eu, 0xb5u, 0x66u, 0x48u, 0x03u, 0xf6u, 0x0eu, 0x61u, 0x35u, 0x57u, 0xb9u, 0x86u, 0xc1u, 0x1du, 0x9eu),
    ubyteArrayOf(0xe1u, 0xf8u, 0x98u, 0x11u, 0x69u, 0xd9u, 0x8eu, 0x94u, 0x9bu, 0x1eu, 0x87u, 0xe9u, 0xceu, 0x55u, 0x28u, 0xdfu),
    ubyteArrayOf(0x8cu, 0xa1u, 0x89u, 0x0du, 0xbfu, 0xe6u, 0x42u, 0x68u, 0x41u, 0x99u, 0x2du, 0x0fu, 0xb0u, 0x54u, 0xbbu, 0x16u)
)
//@formatter:on
