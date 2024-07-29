package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.ExperimentalJwtApi
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents the cryptographic algorithm family used with a key.
 *
 * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.1)
 * @see [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-6)
 */
@JvmInline
@Serializable
@ExperimentalJwtApi
public value class KeyType public constructor(
    public val value: String
) {

    public companion object {

        /**
         * Elliptic Curve [DSS](https://datatracker.ietf.org/doc/html/rfc7518#ref-DSS)
         *
         * @see [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-6)
         */
        public val EC: KeyType = KeyType(value = "EC")

        /**
         * RSA [RFC-3447](https://datatracker.ietf.org/doc/html/rfc3447)
         *
         * @see [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-6)
         */
        public val RSA: KeyType = KeyType(value = "RSA")

        /**
         * Octet sequence (used to represent symmetric keys)
         *
         * @see [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-6)
         */
        public val OCT: KeyType = KeyType(value = "oct")
    }
}
