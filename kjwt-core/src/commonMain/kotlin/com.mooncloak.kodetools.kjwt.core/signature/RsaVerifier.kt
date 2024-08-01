package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.InvalidSignatureKeyAlgorithm
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.crypto.RSASSA_PKCS1_V1_5_VERIFY
import com.mooncloak.kodetools.kjwt.core.crypto.RsaKeyPair
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPrivateKey
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPublicKey
import com.mooncloak.kodetools.kjwt.core.crypto.algorithmIdentifier
import com.mooncloak.kodetools.kjwt.core.crypto.toRsaKeyMaterial
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

@ExperimentalJwtApi
internal data object RsaVerifier : Verifier {

    internal val supportedAlgorithms = setOf(
        SignatureAlgorithm.RS256,
        SignatureAlgorithm.RS384,
        SignatureAlgorithm.RS512
    )

    override suspend fun verify(
        signature: Signature,
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Boolean {
        if (algorithm !in supportedAlgorithms) {
            throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${RsaVerifier::class.simpleName}."
            )
        }

        val rsaVerificationKey = when (val rsaKeyMaterial = key.toRsaKeyMaterial()) {
            is RsaKeyPair -> rsaKeyMaterial.publicKey
            is RsaPublicKey -> rsaKeyMaterial
            is RsaPrivateKey -> throw InvalidSignatureKeyAlgorithm("RSA public keys must be provided to sign, but a private key was provided.")
        }

        return RSASSA_PKCS1_V1_5_VERIFY(
            publicKey = rsaVerificationKey,
            M = input.value.encodeToByteArray(),
            S = signature.value,
            hashAlgorithm = algorithm.algorithmIdentifier
        )
    }
}
