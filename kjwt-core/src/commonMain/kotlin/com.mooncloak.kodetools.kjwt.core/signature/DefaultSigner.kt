package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

/**
 * Retrieves the platform default [Signer] implementation.
 *
 * @see [SignatureAlgorithm] for more information on the supported [SignatureAlgorithm]s.
 */
@ExperimentalJwtApi
public val Signer.Companion.Default: Signer
    get() = DefaultSigner

@ExperimentalJwtApi
internal data object DefaultSigner : Signer {

    override suspend fun sign(
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Signature =
        when (algorithm) {
            in HmacSigner.supportedAlgorithms -> HmacSigner.sign(
                input = input,
                key = key,
                algorithm = algorithm
            )

            in RsaSigner.supportedAlgorithms -> RsaSigner.sign(
                input = input,
                key = key,
                algorithm = algorithm
            )

            else -> throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${DefaultSigner::class.simpleName}."
            )
        }
}
