package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.Header
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm

/**
 * A component that resolves the [Jwk] key used when signing a [Jws]. This could be used to
 * dynamically resolve the signing key for verification or signing purposes.
 */
@ExperimentalJwtApi
public fun interface KeyResolver {

    /**
     * Returns the signing key that should be used to validate a digital signature for the
     * Claims JWS with the specified header and claims.
     *
     * @param header the header of the JWS to validate
     *
     * @return the key that should be used to validate a digital signature for the Claims JWS with
     * the specified header and claims.
     */
    public suspend fun resolve(
        header: Header
    ): Jwk

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
public fun KeyResolver.Companion.of(key: Jwk): KeyResolver =
    StaticKeyResolver(key = key)

/**
 * Retrieves a [KeyResolver] instance whose [KeyResolver.resolve] function always throws an
 * [UnsupportedJwtSignatureAlgorithm]. This could be useful for testing purposes.
 */
@ExperimentalJwtApi
public val KeyResolver.Companion.Unsupported: KeyResolver
    get() = UnsupportedKeyResolver

/**
 * A [KeyResolver] implementation that always returns a single [Jwk] key instance.
 */
@ExperimentalJwtApi
internal class StaticKeyResolver internal constructor(
    private val key: Jwk
) : KeyResolver {

    override suspend fun resolve(header: Header) = key

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StaticKeyResolver) return false

        return key == other.key
    }

    override fun hashCode(): Int = key.hashCode()

    override fun toString(): String = "StaticKeyResolver(key=REDACTED)"
}

@ExperimentalJwtApi
internal data object UnsupportedKeyResolver : KeyResolver {

    override suspend fun resolve(header: Header): Jwk {
        throw UnsupportedJwtSignatureAlgorithm("No signature algorithms are supported.")
    }
}
