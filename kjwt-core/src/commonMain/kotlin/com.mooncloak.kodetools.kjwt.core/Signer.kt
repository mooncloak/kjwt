package com.mooncloak.kodetools.kjwt.core

import org.kotlincrypto.macs.hmac.sha2.HmacSHA256
import org.kotlincrypto.macs.hmac.sha2.HmacSHA384
import org.kotlincrypto.macs.hmac.sha2.HmacSHA512
import kotlin.coroutines.cancellation.CancellationException

/**
 * Represents a component that can be used produce a [Signature].
 */
@ExperimentalJwtApi
public fun interface Signer {

    /**
     * Produces a [Signature] value given the provided signature [input], [key], and [algorithm].
     *
     * @param [input] The signature input [String] that will be signed, resulting in the returned
     * [Signature].
     *
     * @param [key] The [Jwk] that will be used as a signing key.
     *
     * @param [algorithm] The [SignatureAlgorithm] to use.
     *
     * @throws [UnsupportedJwtSignatureAlgorithm] if the provided [algorithm] is not supported by
     * this [Signer].
     *
     * @throws [InvalidSignatureKeyAlgorithm] if the provided [key] is not valid for the provided
     * [algorithm].
     *
     * @return The resulting [Signature] produced by signing the provided [input] value using the
     * provided [key] for the provided signature [algorithm].
     */
    @Throws(
        UnsupportedJwtSignatureAlgorithm::class,
        InvalidSignatureKeyAlgorithm::class,
        CancellationException::class
    )
    public suspend fun sign(
        input: String,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Signature

    public companion object
}

@ExperimentalJwtApi
public val Signer.Companion.Default: Signer
    get() = DefaultSigner

@ExperimentalJwtApi
internal data object DefaultSigner : Signer {

    override suspend fun sign(input: String, key: Jwk, algorithm: SignatureAlgorithm): Signature =
        when {
            algorithm in HmacSigner.supportedAlgorithms -> HmacSigner.sign(
                input = input,
                key = key,
                algorithm = algorithm
            )

            else -> TODO()
        }
}

@ExperimentalJwtApi
internal data object HmacSigner : Signer {

    internal val supportedAlgorithms = setOf(
        SignatureAlgorithm.HS256,
        SignatureAlgorithm.HS384,
        SignatureAlgorithm.HS512
    )

    override suspend fun sign(input: String, key: Jwk, algorithm: SignatureAlgorithm): Signature {
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

                HmacSHA256(keyBytes).doFinal(input.encodeToByteArray())
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

                HmacSHA384(keyBytes).doFinal(input.encodeToByteArray())
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

                HmacSHA512(keyBytes).doFinal(input.encodeToByteArray())
            }

            else -> throw UnsupportedJwtSignatureAlgorithm(
                message = "Signature algorithm '${algorithm.serialName}' is not supported by ${HmacSigner::class.simpleName}."
            )
        }

        return Signature(value = output.decodeToString())
    }
}
