package com.mooncloak.kodetools.aes

/**
 * A collection of functions and utilities for perform encryption and decryption using AES (Advanced Encryption
 * Standard).
 *
 * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
 */
public data object Aes {

    /**
     * Represents the general block encryption function for executing the AES (Advanced Encryption Standard). This
     * function operates on the block-level. Larger datasets should be broken into smaller blocks of size
     * [AesMode.blockSize], padded if necessary, encrypted with this function, and combined using a larger crypto-suite
     * definition.
     *
     * @param [key] The encryption key.
     *
     * @param [input] The data input, which is a block represented as a linear array of 16 bytes.
     *
     * @param [mode] The [AesMode].
     *
     * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
     */
    public fun encryptBlock(
        key: ByteArray,
        input: ByteArray,
        mode: AesMode
    ): ByteArray {
        val roundKeys = expandKey(
            key = key,
            mode = mode
        )

        return encryptBlock(
            numberOfRounds = mode.numberOfRounds,
            roundKeys = roundKeys,
            input = input
        )
    }

    /**
     * Represents the general block decryption function for executing the AES (Advanced Encryption Standard). This
     * function operates on the block-level. Larger datasets should be broken into smaller blocks of size
     * [AesMode.blockSize], padded if necessary, decrypted with this function, and combined using a larger crypto-suite
     * definition.
     *
     * @param [key] The encryption key.
     *
     * @param [input] The data input, which is a block represented as a linear array of 16 bytes.
     *
     * @param [mode] The [AesMode].
     *
     * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
     */
    public fun decryptBlock(
        key: ByteArray,
        input: ByteArray,
        mode: AesMode
    ): ByteArray {
        val roundKeys = expandKey(
            key = key,
            mode = mode
        )

        return decryptBlock(
            numberOfRounds = mode.numberOfRounds,
            roundKeys = roundKeys,
            input = input
        )
    }

    /**
     * Represents the general block encryption function for executing the AES (Advanced Encryption Standard). This
     * function operates on the block-level. Larger datasets should be broken into smaller blocks of size
     * [AesMode.blockSize], padded if necessary, encrypted with this function, and combined using a larger crypto-suite
     * definition.
     *
     * > [!Note]
     * > This overloaded version of this block-level encryption function matches the CIPHER function defined by the AES
     * > Specification and should be called after the key expansion function derived the round keys. This version of
     * > the function is provided as it would be faster for larger datasets instead of having to constantly invoke the
     * > key expansion function.
     *
     * @param [input] The data input, which is a block represented as a linear array of 16 bytes.
     *
     * @param [numberOfRounds] The number of rounds, "Nr", for the instance. This value is dependent on the key bit size.
     * The following values are supported: AES-128: `10`, for AES-192: `12`, AES-256: `14`. The amount of [roundKeys] is
     * defined as the following `4 * (Nr + 1)`, so the default value is deduced from this equation.
     *
     * @param [roundKeys] The keys for each round. A round key is a block that is usually represented as a sequence of
     * four words (16 bytes). The "KEYEXPANSION()" function, defined by the AES Specification, takes the block cipher
     * key as input and generates the round keys as output. This array value should be `4 * (Nr + 1)` in size according
     * to the specification.
     *
     * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
     * @see [Aes.expandKey] For how to retrieve the round keys.
     */
    public fun encryptBlock(
        numberOfRounds: Int,
        roundKeys: Array<ByteArray>,
        input: ByteArray
    ): ByteArray = Cipher(
        input = input,
        numberOfRounds = numberOfRounds,
        roundKeys = roundKeys
    )

    /**
     * Represents the general block decryption function for executing the AES (Advanced Encryption Standard). This
     * function operates on the block-level. Larger datasets should be broken into smaller blocks of size
     * [AesMode.blockSize], padded if necessary, decrypted with this function, and combined using a larger crypto-suite
     * definition.
     *
     * > [!Note]
     * > This overloaded version of this block-level decryption function matches the INVCIPHER function defined by the
     * > AES Specification and should be called after the key expansion function derived the round keys. This version
     * > of the function is provided as it would be faster for larger datasets instead of having to constantly invoke
     * > the key expansion function.
     *
     * @param [input] The data input, which is a block represented as a linear array of 16 bytes.
     *
     * @param [numberOfRounds] The number of rounds, "Nr", for the instance. This value is dependent on the key bit size.
     * The following values are supported: AES-128: `10`, for AES-192: `12`, AES-256: `14`. The amount of [roundKeys] is
     * defined as the following `4 * (Nr + 1)`, so the default value is deduced from this equation.
     *
     * @param [roundKeys] The keys for each round. A round key is a block that is usually represented as a sequence of
     * four words (16 bytes). The "KEYEXPANSION()" function, defined by the AES Specification, takes the block cipher
     * key as input and generates the round keys as output. This array value should be `4 * (Nr + 1)` in size according
     * to the specification.
     *
     * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
     * @see [Aes.expandKey] For how to retrieve the round keys.
     */
    public fun decryptBlock(
        numberOfRounds: Int,
        roundKeys: Array<ByteArray>,
        input: ByteArray
    ): ByteArray = InvCipher(
        input = input,
        numberOfRounds = numberOfRounds,
        roundKeys = roundKeys
    )
}
