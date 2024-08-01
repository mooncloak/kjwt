package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

@ExperimentalJwtApi
internal data object HmacVerifier : Verifier {

    internal val supportedAlgorithms = setOf(
        SignatureAlgorithm.HS256,
        SignatureAlgorithm.HS384,
        SignatureAlgorithm.HS512
    )

    private val signer = HmacSigner

    override suspend fun verify(
        signature: Signature,
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Boolean {
        if (algorithm !in supportedAlgorithms) {
            throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${HmacVerifier::class.simpleName}."
            )
        }

        // The Hmac algorithms use a symmetric key, and therefore, we just perform the signature
        // algorithm again and compare the provided signature with the resulting value.
        val actualSignature = signer.sign(
            input = input,
            key = key,
            algorithm = algorithm
        )

        return signature.value.contentEquals(actualSignature.value)
    }
}
