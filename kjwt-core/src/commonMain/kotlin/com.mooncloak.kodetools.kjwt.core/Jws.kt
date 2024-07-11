package com.mooncloak.kodetools.kjwt.core

import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json

/**
 * Represents a JSON Web Signature (JWS), along with the decoded JSON Web Token (JWT) properties. This is an extension
 * on the [Jwt] interface but provides the [signature] value.
 *
 * The Signature is a result of taking the encoded [Jwt.header] and [Jwt.payload], adding a period character between
 * them, and signing that, along with a secret key, using the signing algorithm specified in the Header. For example:
 *
 * ```
 * val signature = HMACSHA256(
 *     base64UrlEncode(header) + "." +
 *     base64UrlEncode(payload),
 *     secret)
 * ```
 *
 * A [Jws] provides integrity protection for a [Jwt]. Because the Header and Payload of the JWT are signed by the
 * creator of the token, it can easily be verified to make sure that the data was not tampered with. The signature
 * algorithm can practically be any cryptographic signature algorithm, symmetric or asymmetric, but the natively
 * supported options are defined by the [SignatureAlgorithm] enum class.
 *
 * A JWS takes the following encoded form:
 *
 * ```
 * Base64URL-Encoded-Header.Base64URL-Encoded-Payload.Base64URL-Encoded-Signature
 * ```
 *
 * @see [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515)
 * @see [Jwt]
 */
@ExperimentalJwtApi
public interface Jws : Jwt {

    /**
     * The signature represented as a Base64 URL encoded [String] of the signature [ByteArray].
     */
    public val signature: Signature

    public fun interface Validator {

        @Throws(JwtValidationException::class, CancellationException::class)
        public suspend fun validate(
            compacted: CompactedJwt,
            json: Json,
            resolver: KeyResolver,
            validation: suspend Jws.() -> Boolean
        ): Jws
    }

    public fun interface Parser {

        @Throws(JwtParseException::class, CancellationException::class)
        public suspend fun parse(
            compacted: CompactedJwt,
            json: Json,
            resolver: KeyResolver
        ): Jws
    }

    public companion object : Validator by DefaultJwsValidator,
        Parser by DefaultJwsParser
}

@ExperimentalJwtApi
internal expect val DefaultJwsParser: Jws.Parser

@ExperimentalJwtApi
internal val DefaultJwsValidator: Jws.Validator =
    Jws.Validator { compacted, json, resolver, validation ->
        val jws = try {
            Jws.parse(
                compacted = compacted,
                json = json,
                resolver = resolver
            )
        } catch (e: JwtParseException) {
            throw JwtValidationException(
                message = "Error parsing JWS for validation.",
                cause = e
            )
        }

        val result = validation.invoke(jws)

        if (!result) {
            throw JwtValidationException(message = "Validation failed for JWS $jws.")
        }

        jws
    }
