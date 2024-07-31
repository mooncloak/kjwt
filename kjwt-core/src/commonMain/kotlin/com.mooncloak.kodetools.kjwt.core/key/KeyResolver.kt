package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.Header
import com.mooncloak.kodetools.kjwt.core.JwkSet
import com.mooncloak.kodetools.kjwt.core.Jws
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm

/**
 * A component that resolves the [Jwk] key used when signing or verifying a [Jws]. This could be
 * used to dynamically resolve the signing key for verification or signing purposes.
 */
@ExperimentalJwtApi
public fun interface KeyResolver {

    /**
     * Returns the signing key that should be used to validate a digital signature for the
     * Claims JWS with the specified header and claims.
     *
     * @param [header] the header of the JWS to validate
     *
     * @param [operation] The [KeyOperation] that will be performed with the returned key.
     *
     * @return the key that should be used to validate a digital signature for the Claims JWS with
     * the specified header and claims, or `null` if no valid key was found.
     */
    public suspend fun resolve(
        header: Header,
        operation: KeyOperation
    ): Jwk?

    public companion object
}

/**
 * Retrieves a [KeyResolver] instance that always resolves the provided [key] value. This is a
 * convenience function.
 *
 * @param [key] The [Jwk] key that the returned [KeyResolver] will always return.
 *
 * @return A [KeyResolver] whose [KeyResolver.resolve] function always returns the provided [key].
 */
@ExperimentalJwtApi
public fun KeyResolver.Companion.of(key: Jwk?): KeyResolver =
    StaticKeyResolver(key = key)

/**
 * Retrieves a [KeyResolver] instance that chooses from [Jwk]s defined in the provided [JwkSet] by
 * comparing the [Jwk.keyId] value with the [Header.keyId] value.
 *
 * @param [keys] The [JwkSet] to used to retrieve [Jwk]s.
 *
 * @return A [KeyResolver] whose [KeyResolver.resolve] function returns a [Jwk] from the provided
 * [keys], or `null` if there is no match.
 */
@ExperimentalJwtApi
public fun KeyResolver.Companion.of(keys: JwkSet): KeyResolver =
    StaticJwkSetKeyResolver(keySet = keys)

/**
 * Retrieves a [KeyResolver] instance whose [KeyResolver.resolve] function always throws an
 * [UnsupportedJwtSignatureAlgorithm]. This could be useful for testing purposes.
 */
@ExperimentalJwtApi
public val KeyResolver.Companion.Unsupported: KeyResolver
    get() = UnsupportedKeyResolver

/**
 * Retrieves a [KeyResolver] instance whose [KeyResolver.resolve] function always returns `null`.
 * This could be useful for testing purposes.
 */
@ExperimentalJwtApi
public val KeyResolver.Companion.AlwaysNull: KeyResolver
    get() = AlwaysNullKeyResolver

/**
 * A [KeyResolver] implementation that always returns a single [Jwk] key instance.
 */
@ExperimentalJwtApi
internal class StaticKeyResolver internal constructor(
    private val key: Jwk?
) : KeyResolver {

    override suspend fun resolve(
        header: Header,
        operation: KeyOperation
    ): Jwk? = key

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StaticKeyResolver) return false

        return key == other.key
    }

    override fun hashCode(): Int = key.hashCode()

    override fun toString(): String = "StaticKeyResolver(key=REDACTED)"
}

@ExperimentalJwtApi
internal class StaticJwkSetKeyResolver internal constructor(
    private val keySet: JwkSet
) : KeyResolver {

    override suspend fun resolve(
        header: Header,
        operation: KeyOperation
    ): Jwk? {
        if (header.keyId == null) return null

        return keySet.jwkKeys.firstOrNull { key -> key.keyId == header.keyId }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StaticJwkSetKeyResolver) return false

        return keySet == other.keySet
    }

    override fun hashCode(): Int =
        keySet.hashCode()

    override fun toString(): String =
        "StaticJwkSetKeyResolver(keySet=REDACTED)"
}

@ExperimentalJwtApi
internal data object UnsupportedKeyResolver : KeyResolver {

    override suspend fun resolve(
        header: Header,
        operation: KeyOperation
    ): Jwk {
        throw UnsupportedJwtSignatureAlgorithm("No signature algorithms are supported.")
    }
}

@ExperimentalJwtApi
internal data object AlwaysNullKeyResolver : KeyResolver {

    override suspend fun resolve(
        header: Header,
        operation: KeyOperation
    ): Jwk? = null
}
