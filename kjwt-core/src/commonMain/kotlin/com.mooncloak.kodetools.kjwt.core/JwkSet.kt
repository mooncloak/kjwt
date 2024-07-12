package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a JWK (JSON Web Key) Set.
 *
 * @property [keys] A [List] of [Jwk] key values that this [JwkSet] contains.
 *
 * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517)
 */
@Serializable
@ExperimentalJwtApi
public class JwkSet public constructor(
    @SerialName(value = PropertyKey.KEYS) public val keys: List<Jwk> = emptyList()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JwkSet) return false

        return keys == other.keys
    }

    override fun hashCode(): Int =
        keys.hashCode()

    override fun toString(): String =
        "JwkSet(keys=$keys)"

    public object PropertyKey {

        public const val KEYS: String = "keys"
    }

    public companion object
}
