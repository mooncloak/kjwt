package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

/**
 * Retrieves the platform default [Verifier] implementation.
 *
 * @see [SignatureAlgorithm] for more information on the supported [SignatureAlgorithm]s.
 * @see [Signer.Companion.Default] for the default companion [Signer] implementation.
 */
@ExperimentalJwtApi
public val Verifier.Companion.Default: Verifier
    get() = DefaultVerifier

@ExperimentalJwtApi
internal data object DefaultVerifier : Verifier {

    override suspend fun verify(
        signature: Signature,
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Boolean =
        when (algorithm) {
            in HmacSigner.supportedAlgorithms -> HmacVerifier.verify(
                signature = signature,
                input = input,
                key = key,
                algorithm = algorithm
            )

            in RsaVerifier.supportedAlgorithms -> RsaVerifier.verify(
                signature = signature,
                input = input,
                key = key,
                algorithm = algorithm
            )

            else -> throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${DefaultVerifier::class.simpleName}."
            )
        }
}
