package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.Jws
import com.mooncloak.kodetools.kjwt.core.Jwt
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.key.KeyResolver
import kotlin.coroutines.cancellation.CancellationException

/**
 * A component that can be signed into a [Jws].
 */
@ExperimentalJwtApi
public fun interface Signable {

    /**
     * Signs this [Jwt] using the provided [algorithm] and [key] to create a [Jws].
     *
     * @param [resolver] The [KeyResolver] used to obtain the [Jwk] used for signing the JWT. Note
     * that this value may be `null`, if the provided [algorithm] is [SignatureAlgorithm.NONE]. If
     * this value is `null`, in any other case, then an exception will be thrown.
     *
     * @param [algorithm] The [SignatureAlgorithm] used for signing the JWT.
     *
     * @throws [UnsupportedJwtSignatureAlgorithm] if the provided [algorithm] is unsupported by the
     * platform, or the key obtained from the [resolver] was required for the [algorithm] but
     * `null` was provided.
     *
     * @return The signed [Jws].
     *
     * @see [SignatureAlgorithm] for more information on the supported [SignatureAlgorithm]s.
     */
    @Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
    public suspend fun sign(
        resolver: KeyResolver,
        algorithm: SignatureAlgorithm
    ): Jws

    public companion object
}
