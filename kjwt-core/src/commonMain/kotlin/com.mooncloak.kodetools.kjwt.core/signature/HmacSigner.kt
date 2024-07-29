package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.InvalidSignatureKeyAlgorithm
import com.mooncloak.kodetools.kjwt.core.Jwk
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import org.kotlincrypto.macs.hmac.sha2.HmacSHA384
import org.kotlincrypto.macs.hmac.sha2.HmacSHA512

@ExperimentalJwtApi
internal data object HmacSigner : Signer {

    internal val supportedAlgorithms = setOf(
        SignatureAlgorithm.HS256,
        SignatureAlgorithm.HS384,
        SignatureAlgorithm.HS512
    )

    override suspend fun sign(
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Signature {
        // See JWA Specification:
        // https://www.rfc-editor.org/rfc/rfc7518.html#section-3.2

        if (algorithm !in supportedAlgorithms) {
            throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${HmacSigner::class.simpleName}."
            )
        }

        val k = key.k ?: throw InvalidSignatureKeyAlgorithm(
            message = "JWK '${Jwk.PropertyKey.K}' is required for '${algorithm.serialName}'."
        )

        val keyBytes = k.encodeToByteArray()

        val output = when (algorithm) {
            SignatureAlgorithm.HS256 -> {
                // A key of the same size as the has output or larger is required for the
                // algorithm, according to the specification. SHA-256 has an output size of 256
                // bits, or 32 bytes.
                if (keyBytes.size < 32) {
                    throw InvalidSignatureKeyAlgorithm(
                        message = "Signing key MUST have a size equal to or greater than the has output size for '${algorithm.serialName}'. Required byte size is '32', actual key size was '${keyBytes.size}'."
                    )
                }

                HmacSHA256(keyBytes).doFinal(input.value.encodeToByteArray())
            }

            SignatureAlgorithm.HS384 -> {
                // A key of the same size as the has output or larger is required for the
                // algorithm, according to the specification. SHA-256 has an output size of 384
                // bits, or 48 bytes.
                if (keyBytes.size < 48) {
                    throw InvalidSignatureKeyAlgorithm(
                        message = "Signing key MUST have a size equal to or greater than the has output size for '${algorithm.serialName}'. Required byte size is '48', actual key size was '${keyBytes.size}'."
                    )
                }

                HmacSHA384(keyBytes).doFinal(input.value.encodeToByteArray())
            }

            SignatureAlgorithm.HS512 -> {
                // A key of the same size as the has output or larger is required for the
                // algorithm, according to the specification. SHA-512 has an output size of 512
                // bits, or 64 bytes.
                if (keyBytes.size < 64) {
                    throw InvalidSignatureKeyAlgorithm(
                        message = "Signing key MUST have a size equal to or greater than the has output size for '${algorithm.serialName}'. Required byte size is '64', actual key size was '${keyBytes.size}'."
                    )
                }

                HmacSHA512(keyBytes).doFinal(input.value.encodeToByteArray())
            }

            else -> throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${HmacSigner::class.simpleName}."
            )
        }

        return Signature(value = output.decodeToString())
    }
}
