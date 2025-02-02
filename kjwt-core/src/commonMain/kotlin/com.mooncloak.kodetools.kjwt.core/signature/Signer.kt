package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.InvalidSignatureKeyAlgorithm
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import kotlin.coroutines.cancellation.CancellationException

/**
 * Represents a component that can be used to produce a [Signature].
 *
 * @see [Signer.Companion.Default] for a default implementation of this interface.
 */
@ExperimentalJwtApi
public fun interface Signer {

    /**
     * Produces a [Signature] value given the provided signature [input], [key], and [algorithm].
     *
     * @param [input] The [SignatureInput] that will be signed, resulting in the returned
     * [Signature].
     *
     * @param [key] The [Jwk] that will be used as a signing key.
     *
     * @param [algorithm] The [SignatureAlgorithm] to use.
     *
     * @throws [UnsupportedJwtSignatureAlgorithm] if the provided [algorithm] is not supported by
     * this [Signer].
     *
     * @throws [InvalidSignatureKeyAlgorithm] if the provided [key] is not valid for the provided
     * [algorithm].
     *
     * @return The resulting [Signature] produced by signing the provided [input] value using the
     * provided [key] for the provided signature [algorithm].
     */
    @Throws(
        UnsupportedJwtSignatureAlgorithm::class,
        InvalidSignatureKeyAlgorithm::class,
        CancellationException::class
    )
    public suspend fun sign(
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Signature

    public companion object
}
