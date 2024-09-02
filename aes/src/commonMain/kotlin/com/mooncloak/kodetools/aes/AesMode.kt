package com.mooncloak.kodetools.aes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the supported AES (Advanced Encryption Standard) mode. AES can be performed with 128-bit, 192-bit, or
 * 256-bit keys.
 *
 * @see [AES Specification](https://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.197-upd1.pdf)
 */
@Serializable
public enum class AesMode {

    @SerialName(value = "aes_128")
    AES_128,

    @SerialName(value = "aes_192")
    AES_192,

    @SerialName(value = "aes_256")
    AES_256;

    public companion object
}

/**
 * The number of rounds for the AES encryption and decryption process for this [AesMode]. This value is represented as
 * `Nr` in the AES Specification.
 */
public val AesMode.numberOfRounds: Int
    inline get() = when (this) {
        AesMode.AES_128 -> 10
        AesMode.AES_192 -> 12
        AesMode.AES_256 -> 14
    }

/**
 * The required input data size for the AES encryption and decryption block-level functions.
 */
@Suppress("UnusedReceiverParameter")
public val AesMode.blockSize: Int
    inline get() = 16

/**
 * Retrieves the required amount of bits the encryption key should be for this particular [AesMode].
 */
public val AesMode.keyBitSize: Int
    inline get() = when (this) {
        AesMode.AES_128 -> 128
        AesMode.AES_192 -> 192
        AesMode.AES_256 -> 256
    }

/**
 * The number of 32-bit words comprising the key. This value is represented as `Nk` in the AES Specification. This
 * value is simply the result of the operation: `keyBitSize / 32`.
 */
public val AesMode.numberOfWords: Int
    inline get() = when (this) {
        AesMode.AES_128 -> 4
        AesMode.AES_192 -> 6
        AesMode.AES_256 -> 8
    }
