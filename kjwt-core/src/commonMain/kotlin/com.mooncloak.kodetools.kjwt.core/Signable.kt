package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import kotlin.coroutines.cancellation.CancellationException

/**
 * A component that can be signed into a [Jws].
 */
@ExperimentalJwtApi
public fun interface Signable {

    /**
     * Signs this [Jwt] using the provided [algorithm] and [key] to create a [Jws].
     *
     * @param [key] The [Key] used for signing the JWT. Note that this value may be `null`, if the
     * provided [algorithm] is [SignatureAlgorithm.NONE]. If this value is `null`, in any other
     * case, then an exception will be thrown.
     *
     * @param [algorithm] The [SignatureAlgorithm] used for signing the JWT.
     *
     * @throws [UnsupportedJwtSignatureAlgorithm] if the provided [algorithm] is unsupported by the
     * platform, or the [key] was required for the [algorithm] but `null` was provided.
     *
     * @return The signed [Jws].
     *
     * @see [SignatureAlgorithm] for more information on the supported [SignatureAlgorithm]s.
     */
    @ExperimentalKeyApi
    @Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
    public suspend fun sign(
        key: Key?,
        algorithm: SignatureAlgorithm
    ): Jws

    public companion object
}
