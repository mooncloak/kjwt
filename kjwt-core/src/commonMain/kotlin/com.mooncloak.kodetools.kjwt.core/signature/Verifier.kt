package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.InvalidSignatureKeyAlgorithm
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlin.coroutines.cancellation.CancellationException

/**
 * Represents a component that can be used to verify that a [Signature] is valid.
 *
 * @see [Verifier.Companion.Default] for a default implementation of this interface.
 */
@ExperimentalJwtApi
public fun interface Verifier {

    /**
     * Determines whether the provided [signature] is valid for the provided signature [input],
     * [key], and [algorithm].
     *
     * @param [signature] The [Signature] to verify.
     *
     * @param [input] The [SignatureInput] that was used to produce the [signature].
     *
     * @param [key] The [Jwk] that will be used to verify the [signature].
     *
     * @param [algorithm] The [SignatureAlgorithm] that was used to produce the [signature].
     *
     * @throws [UnsupportedJwtSignatureAlgorithm] if the provided [algorithm] is not supported by
     * this [Verifier].
     *
     * @throws [InvalidSignatureKeyAlgorithm] if the provided [key] is not valid for the provided
     * [algorithm].
     *
     * @return `true` if the [signature] is verified and valid, `false` otherwise.
     */
    @Throws(
        UnsupportedJwtSignatureAlgorithm::class,
        InvalidSignatureKeyAlgorithm::class,
        CancellationException::class
    )
    public suspend fun verify(
        signature: Signature,
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Boolean

    public companion object
}
