package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.json.Json
import org.kotlincrypto.SecureRandom
import kotlin.coroutines.cancellation.CancellationException
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Generates a new [Jwk] value with random key data for this particular [SignatureAlgorithm]. Note
 * that the returned [Jwk] should contain both the private and public values for asymmetric keys.
 *
 * @receiver The [SignatureAlgorithm] that the generated [Jwk] will be used with.
 *
 * @param [json] The [Json] instance used for key serialization and deserialization.
 *
 * @throws [UnsupportedJwtSignatureAlgorithm] if the [SignatureAlgorithm] is not supported.
 *
 * @return The generated [Jwk], or `null` if the [SignatureAlgorithm] does not require a signing
 * key.
 */
@OptIn(ExperimentalEncodingApi::class)
@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
public suspend fun SignatureAlgorithm.generateSigningKey(
    json: Json = Json.Default
): Jwk? =
    when (this) {
        SignatureAlgorithm.NONE -> null

        SignatureAlgorithm.HS256 -> Jwk(
            keyType = KeyType.OCT,
            json = json
        ) {
            use = KeyUse.Sig
            keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
            signatureAlgorithm = SignatureAlgorithm.HS256

            // 32 bytes = 256 bits
            val randomBytes = SecureRandom().nextBytesOf(32)
            k = Base64.UrlSafe.encode(source = randomBytes)
        }

        SignatureAlgorithm.HS384 -> Jwk(
            keyType = KeyType.OCT,
            json = json
        ) {
            use = KeyUse.Sig
            keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
            signatureAlgorithm = SignatureAlgorithm.HS256

            // 48 bytes = 384 bits
            val randomBytes = SecureRandom().nextBytesOf(48)
            k = Base64.UrlSafe.encode(source = randomBytes)
        }

        SignatureAlgorithm.HS512 -> Jwk(
            keyType = KeyType.OCT,
            json = json
        ) {
            use = KeyUse.Sig
            keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
            signatureAlgorithm = SignatureAlgorithm.HS256

            // 64 bytes = 512 bits
            val randomBytes = SecureRandom().nextBytesOf(64)
            k = Base64.UrlSafe.encode(source = randomBytes)
        }

        SignatureAlgorithm.RS256 -> TODO()

        SignatureAlgorithm.RS384 -> TODO()

        SignatureAlgorithm.RS512 -> TODO()

        else -> throw UnsupportedJwtSignatureAlgorithm("Signature algorithm '${this.serialName}' is not supported.")
    }

/**
 * Generates a new [Jwk] value with random key data for this particular [SignatureAlgorithm]. Note
 * that the returned [Jwk] should contain both the private and public values for asymmetric keys.
 *
 * @receiver The [SignatureAlgorithm] that the generated [Jwk] will be used with.
 *
 * @param [json] The [Json] instance used for key serialization and deserialization.
 *
 * @return The generated [Jwk], or `null` if the [SignatureAlgorithm] does not require a signing
 * key or the [SignatureAlgorithm] is not supported to create a signing key.
 */
@ExperimentalJwtApi
public suspend fun SignatureAlgorithm.generateSigningKeyOrNull(
    json: Json = Json.Default
): Jwk? = try {
    this.generateSigningKey(json = json)
} catch (_: UnsupportedJwtSignatureAlgorithm) {
    null
}
