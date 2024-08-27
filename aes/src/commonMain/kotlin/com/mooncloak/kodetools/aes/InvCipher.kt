package com.mooncloak.kodetools.aes

/**
 * Represents the general decryption function for executing the AES (Advanced Encryption Standard).
 *
 * @param [input] The data input, which is a block represented as a linear array of 16 bytes.
 *
 * @param [numberOfRounds] The number of rounds, "Nr", for the instance. This value is dependent on the key bit size.
 * The following values are supported: AES-128: `10`, for AES-192: `12`, AES-256: `14`.
 *
 * @param [roundKeys] The keys for each round. This value must be of size [numberOfRounds]. A round key is a block that
 * is usually represented as a sequence of four words (16 bytes). The "KEYEXPANSION()" function, defined by the AES
 * Specification, takes the block cipher key as input and generates the round keys as output.
 *
 * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
 */
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
