package com.mooncloak.kodetools.kjwt.core.key

import com.benasher44.uuid.uuid4
import com.mooncloak.kodetools.kjwt.core.KeyGenerationException
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.json.Json
import org.kotlincrypto.SecureRandom
import kotlin.coroutines.cancellation.CancellationException
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * A component that generates a [Jwk].
 */
@ExperimentalJwtApi
public fun interface KeyGenerator {

    /**
     * Generates a new [Jwk] instance with random key data.
     *
     * @throws [KeyGenerationException] if the generation of a key failed.
     *
     * @return The generated [Jwk], or `null` if a key could not be generated but was not the
     * result of an exception.
     */
    @Throws(KeyGenerationException::class, CancellationException::class)
    public suspend fun generate(): Jwk?

    public companion object
}

/**
 * Generates a new [Jwk] instance with random key data.
 *
 * @return The generated [Jwk], or `null` if a key could not be generated or an exception occurred.
 */
@ExperimentalJwtApi
public suspend fun KeyGenerator.generateOrNull(): Jwk? =
    try {
        generate()
    } catch (_: KeyGenerationException) {
        null
    }

/**
 * Creates a [KeyGenerator] that generates signing keys for the provided signature [algorithm].
 *
 * @param [algorithm] The [SignatureAlgorithm] that the resulting generated [Jwk]s will be
 * compatible with.
 *
 * @param [json] The [Json] instance to be used in the creation of the [Jwk]s.
 *
 * @return A [KeyGenerator] that generates keys for use with the provided signing [algorithm].
 */
@ExperimentalJwtApi
public fun KeyGenerator.Companion.signingKey(
    algorithm: SignatureAlgorithm,
    json: Json = Json.Default
): KeyGenerator = SigningKeyGenerator(
    algorithm = algorithm,
    json = json
)

@OptIn(ExperimentalEncodingApi::class)
@ExperimentalJwtApi
internal class SigningKeyGenerator internal constructor(
    private val algorithm: SignatureAlgorithm,
    private val json: Json
) : KeyGenerator {

    override suspend fun generate(): Jwk? {
        val random = SecureRandom()

        return when (algorithm) {
            SignatureAlgorithm.NONE -> null

            SignatureAlgorithm.HS256 -> Jwk(
                keyType = KeyType.OCT,
                json = json
            ) {
                keyId = uuid4().toString()
                use = KeyUse.Sig
                keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
                signatureAlgorithm = SignatureAlgorithm.HS256

                // 32 bytes = 256 bits
                val randomBytes = random.nextBytesOf(32)
                k = Base64.UrlSafe.encode(source = randomBytes)
            }

            SignatureAlgorithm.HS384 -> Jwk(
                keyType = KeyType.OCT,
                json = json
            ) {
                keyId = uuid4().toString()
                use = KeyUse.Sig
                keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
                signatureAlgorithm = SignatureAlgorithm.HS256

                // 48 bytes = 384 bits
                val randomBytes = random.nextBytesOf(48)
                k = Base64.UrlSafe.encode(source = randomBytes)
            }

            SignatureAlgorithm.HS512 -> Jwk(
                keyType = KeyType.OCT,
                json = json
            ) {
                keyId = uuid4().toString()
                use = KeyUse.Sig
                keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
                signatureAlgorithm = SignatureAlgorithm.HS256

                // 64 bytes = 512 bits
                val randomBytes = random.nextBytesOf(64)
                k = Base64.UrlSafe.encode(source = randomBytes)
            }

            SignatureAlgorithm.RS256, SignatureAlgorithm.RS384, SignatureAlgorithm.RS512 -> generateRsaSigningKey(
                algorithm = algorithm,
                bitCount = 4096,
                json = json
            )

            else -> throw KeyGenerationException(
                cause = UnsupportedJwtSignatureAlgorithm("Signature algorithm '${algorithm.serialName}' is not supported.")
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SigningKeyGenerator) return false

        if (algorithm != other.algorithm) return false

        return json == other.json
    }

    override fun hashCode(): Int {
        var result = algorithm.hashCode()
        result = 31 * result + json.hashCode()
        return result
    }

    override fun toString(): String =
        "SigningKeyGenerator(algorithm=$algorithm, json=$json)"
}
