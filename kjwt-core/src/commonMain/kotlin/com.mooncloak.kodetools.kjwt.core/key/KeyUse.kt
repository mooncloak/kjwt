package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.ExperimentalJwtApi
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents what a key is used for (signature, encryption, etc.)
 */
@Serializable
@JvmInline
@ExperimentalJwtApi
public value class KeyUse public constructor(
    public val value: String
) {

    public companion object {

        /**
         * Indicates that a key is used for signature purposes.
         */
        public val Sig: KeyUse = KeyUse(value = "sig")

        /**
         * Indicates that a key is used for encryption purposes.
         */
        public val Enc: KeyUse = KeyUse(value = "enc")
    }
}
