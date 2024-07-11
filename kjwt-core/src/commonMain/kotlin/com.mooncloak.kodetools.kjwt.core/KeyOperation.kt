package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Identifies the operations for which the key is intended to be used. Values defined by the
 * specification are:
 *
 * - "sign" (compute digital signature or MAC)
 * - "verify" (verify digital signature or MAC)
 * - "encrypt" (encrypt content)
 * - "decrypt" (decrypt content and validate decryption, if applicable)
 * - "wrapKey" (encrypt key)
 * - "unwrapKey" (decrypt key and validate decryption, if applicable)
 * - "deriveKey" (derive key)
 * - "deriveBits" (derive bits not to be used as a key)
 */
@Serializable
@JvmInline
@ExperimentalJwtApi
public value class KeyOperation public constructor(
    public val value: String
) {

    public companion object {

        public val Sign: KeyOperation = KeyOperation(value = "sign")

        public val Verify: KeyOperation = KeyOperation(value = "verify")

        public val Encrypt: KeyOperation = KeyOperation(value = "encrypt")

        public val Decrypt: KeyOperation = KeyOperation(value = "decrypt")

        public val WrapKey: KeyOperation = KeyOperation(value = "wrapKey")

        public val UnwrapKey: KeyOperation = KeyOperation(value = "unwrapKey")

        public val DeriveKey: KeyOperation = KeyOperation(value = "deriveKey")

        public val DeriveBits: KeyOperation = KeyOperation(value = "deriveBits")
    }
}
