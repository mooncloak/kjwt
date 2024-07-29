package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Identifies the operations for which the key is intended to be used. Values defined by the
 * specification are:
 *
 * - [Sign] (compute digital signature or MAC)
 * - [Verify] (verify digital signature or MAC)
 * - [Encrypt] (encrypt content)
 * - [Decrypt] (decrypt content and validate decryption, if applicable)
 * - [WrapKey] (encrypt key)
 * - [UnwrapKey] (decrypt key and validate decryption, if applicable)
 * - [DeriveKey] (derive key)
 * - [DeriveBits] (derive bits not to be used as a key)
 */
@Serializable
@JvmInline
@ExperimentalJwtApi
public value class KeyOperation public constructor(
    public val value: String
) {

    public companion object {

        /**
         * Compute digital signature or MAC.
         */
        public val Sign: KeyOperation = KeyOperation(value = "sign")

        /**
         * Verify digital signature or MAC.
         */
        public val Verify: KeyOperation = KeyOperation(value = "verify")

        /**
         * Encrypt content.
         */
        public val Encrypt: KeyOperation = KeyOperation(value = "encrypt")

        /**
         * Decrypt content and validate decryption, if applicable.
         */
        public val Decrypt: KeyOperation = KeyOperation(value = "decrypt")

        /**
         * Encrypt key.
         */
        public val WrapKey: KeyOperation = KeyOperation(value = "wrapKey")

        /**
         * Decrypt key and validate decryption, if applicable.
         */
        public val UnwrapKey: KeyOperation = KeyOperation(value = "unwrapKey")

        /**
         * Derive key.
         */
        public val DeriveKey: KeyOperation = KeyOperation(value = "deriveKey")

        /**
         * Derive bits not to be used as a key.
         */
        public val DeriveBits: KeyOperation = KeyOperation(value = "deriveBits")
    }
}
