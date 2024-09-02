package com.mooncloak.kodetools.aes

/**
 * Represents the general encryption function for executing the AES (Advanced Encryption Standard).
 *
 * @param [input] The data input, which is a block represented as a linear array of 16 bytes.
 *
 * @param [numberOfRounds] The number of rounds, "Nr", for the instance. This value is dependent on the key bit size.
 * The following values are supported: AES-128: `10`, for AES-192: `12`, AES-256: `14`. The amount of [roundKeys] is
 * defined as the following `4 * (Nr + 1)`, so the default value is deduced from this equation.
 *
 * @param [roundKeys] The keys for each round. A round key is a block that is usually represented as a sequence of four
 * words (16 bytes). The "KEYEXPANSION()" function, defined by the AES Specification, takes the block cipher key as
 * input and generates the round keys as output. This array value should be `4 * (Nr + 1)` in size according to the
 * specification.
 *
 * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
 */
@Suppress("FunctionName")
internal fun Cipher(
    input: ByteArray,
    roundKeys: Array<ByteArray>,
    numberOfRounds: Int = (roundKeys.size / 4) - 1
): ByteArray {
    require(input.size == 16) { "A block input MUST be 16 bytes. Instead, ${input.size} bytes were provided." }
    require(numberOfRounds in arrayOf(10, 12, 14)) {
        "The 'numberOfRounds' value MUST be one of the following values: 10, 12, 14."
    }

    val state = State(input)

    state.addRoundKey(roundKeys.copyOfRange(fromIndex = 0, toIndex = 4))

    for (round in 1 until numberOfRounds) {
        state.subBytes()
        state.shiftRows()
        state.mixColumns()
        state.addRoundKey(roundKeys.copyOfRange(fromIndex = 4 * round, toIndex = 4 * round + 4))
    }

    state.subBytes()
    state.shiftRows()
    state.addRoundKey(roundKeys.copyOfRange(fromIndex = 4 * numberOfRounds, toIndex = 4 * numberOfRounds + 3))

    return state.output()
}

/**
 * Represents the general decryption function for executing the AES (Advanced Encryption Standard).
 *
 * @param [input] The data input, which is a block represented as a linear array of 16 bytes.
 *
 * @param [numberOfRounds] The number of rounds, "Nr", for the instance. This value is dependent on the key bit size.
 * The following values are supported: AES-128: `10`, for AES-192: `12`, AES-256: `14`. The amount of [roundKeys] is
 * defined as the following `4 * (Nr + 1)`, so the default value is deduced from this equation.
 *
 * @param [roundKeys] The keys for each round. A round key is a block that is usually represented as a sequence of four
 * words (16 bytes). The "KEYEXPANSION()" function, defined by the AES Specification, takes the block cipher key as
 * input and generates the round keys as output. This array value should be `4 * (Nr + 1)` in size according to the
 * specification.
 *
 * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
 */
@Suppress("FunctionName")
internal fun InvCipher(
    input: ByteArray,
    numberOfRounds: Int,
    roundKeys: Array<ByteArray>
): ByteArray {
    val state = State(input)

    state.addRoundKey(roundKeys.copyOfRange(fromIndex = 4 * numberOfRounds, toIndex = 4 * numberOfRounds + 3))

    for (round in (numberOfRounds - 1) downTo 1) {
        state.invShiftRows()
        state.invSubBytes()
        state.addRoundKey(roundKeys.copyOfRange(fromIndex = 4 * round, toIndex = 4 * round + 3))
        state.invMixColumns()
    }

    state.invShiftRows()
    state.invSubBytes()
    state.addRoundKey(roundKeys.copyOfRange(fromIndex = 0, toIndex = 4))

    return state.output()
}
