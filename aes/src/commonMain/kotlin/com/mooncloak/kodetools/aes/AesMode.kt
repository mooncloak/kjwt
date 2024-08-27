package com.mooncloak.kodetools.aes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

public val AesMode.numberOfRounds: Int
    inline get() = when (this) {
        AesMode.AES_128 -> 10
        AesMode.AES_192 -> 12
        AesMode.AES_256 -> 14
    }

@Suppress("UnusedReceiverParameter")
public val AesMode.blockSize: Int
    inline get() = 16
