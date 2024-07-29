package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.InvalidSignatureKeyAlgorithm
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.crypto.AlgorithmIdentifier
import com.mooncloak.kodetools.kjwt.core.crypto.RSASSA_PKCS1_v1_5_SIGN
import com.mooncloak.kodetools.kjwt.core.crypto.RsaKeyPair
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPrivateKey
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPublicKey
import com.mooncloak.kodetools.kjwt.core.crypto.toRsaKeyMaterial
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.key.Jwk

@ExperimentalJwtApi
internal data object RsaSigner : Signer {

    internal val supportedAlgorithms = setOf(
        SignatureAlgorithm.RS256,
        SignatureAlgorithm.RS384,
        SignatureAlgorithm.RS512
    )

    override suspend fun sign(
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Signature {
        if (algorithm !in supportedAlgorithms) {
            throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${RsaSigner::class.simpleName}."
            )
        }

        val rsaSigningKey = when (val rsaKeyMaterial = key.toRsaKeyMaterial()) {
            is RsaKeyPair -> rsaKeyMaterial.privateKey
            is RsaPrivateKey -> rsaKeyMaterial
            is RsaPublicKey -> throw InvalidSignatureKeyAlgorithm("RSA private keys must be provided to sign, but a public key was provided.")
        }

        val hashAlgorithm = when (algorithm) {
            SignatureAlgorithm.RS256 -> AlgorithmIdentifier.SHA256
            SignatureAlgorithm.RS384 -> AlgorithmIdentifier.SHA384
            SignatureAlgorithm.RS512 -> AlgorithmIdentifier.SHA512
            else -> throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${RsaSigner::class.simpleName}."
            )
        }

        val value = RSASSA_PKCS1_v1_5_SIGN(
            K = rsaSigningKey,
            M = input.value.encodeToByteArray(),
            k = 0, // FIXME: This should be derived by the function
            hashAlgorithm = hashAlgorithm
        ).decodeToString()

        return Signature(value = value)
    }
}
