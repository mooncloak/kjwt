package com.mooncloak.kodetools.aes

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Defines how large sets of data are combined during AES encryption. AES encrypts 16 byte blocks of data, but for data
 * larger than 16 bytes, they have to be combined. The approach to how large data is combined (for example,
 * concatenated, or XORed with the previous cipher text, etc.) is defined by separate specifications and identified by
 * instances of this class. Note that only some common modes are supported by this AES implementation.
 */
@JvmInline
@Serializable
public value class BlockCipherMode public constructor(
    public val value: String
) {

    public companion object {

        public val ECB: BlockCipherMode = BlockCipherMode(value = "ECB")

        public val CBC: BlockCipherMode = BlockCipherMode(value = "CBC")

        public val GCM: BlockCipherMode = BlockCipherMode(value = "GCM")
    }
}
