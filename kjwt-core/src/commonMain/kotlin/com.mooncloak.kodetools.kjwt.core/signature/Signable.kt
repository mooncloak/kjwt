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
     * Signs this [Jwt] using the provided key retrieved from the [resolver] and the
     * [SignatureAlgorithm] retrieved from the JWT header to create a [Jws].
     *
     * @param [resolver] The [KeyResolver] used to obtain the [Jwk] used for signing the JWT. Note
     * that this value may be `null`, if the retrieved [SignatureAlgorithm] is
     * [SignatureAlgorithm.NONE]. If this value is `null`, in any other case, then an exception
     * will be thrown.
     *
     * @throws [UnsupportedJwtSignatureAlgorithm] if the retrieved [SignatureAlgorithm] is
     * unsupported by the platform, or the key obtained from the [resolver] was required for the
     * [SignatureAlgorithm] but `null` was provided.
     *
     * @return The signed [Jws].
     *
     * @see [SignatureAlgorithm] for more information on the supported [SignatureAlgorithm]s.
     */
    @Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
    public suspend fun sign(
        resolver: KeyResolver
    ): Jws

    public companion object
}
