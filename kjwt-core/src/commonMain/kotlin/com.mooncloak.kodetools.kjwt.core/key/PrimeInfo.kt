package com.mooncloak.kodetools.kjwt.core.key

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a prime info object for the [Jwk.oth] property defined by the
 * [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.7).
 *
 * @property [r] The "r" (prime factor) parameter within an "oth" array member represents the value
 * of a subsequent prime factor. It is represented as a Base64urlUInt-encoded value.
 *
 * @property [d] The "d" (factor CRT exponent) parameter within an "oth" array member represents
 * the CRT exponent of the corresponding prime factor. It is represented as a Base64urlUInt-encoded
 * value.
 *
 * @property [t] The "t" (factor CRT coefficient) parameter within an "oth" array member represents
 * the CRT coefficient of the corresponding prime factor. It is represented as a
 * Base64urlUInt-encoded value.
 *
 * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.7)
 */
@Serializable
public data class PrimeInfo public constructor(
    @SerialName(value = PropertyKey.R) public val r: String,
    @SerialName(value = PropertyKey.D) public val d: String,
    @SerialName(value = PropertyKey.T) public val t: String
) {

    public object PropertyKey {

        /**
         * The key for the [PrimeInfo.r] property.
         */
        public const val R: String = "r"

        /**
         * The key for the [PrimeInfo.d] property
         */
        public const val D: String = "d"

        /**
         * The key for the [PrimeInfo.t] property.
         */
        public const val T: String = "t"
    }
}
