package com.mooncloak.kodetools.kjwt.core

import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json

/**
 * Represents a decoded JSON Web Token (JWT) without the Signature defined in a [Jws]. A JWT takes
 * the following encoded form:
 *
 * ```
 * Base64URL-Encoded-Header.Base64URL-Encoded-Payload
 * ```
 *
 * The [header] is a JSON object, which when encoded it is turned into a JSON [String] and Base64
 * URL encoded.
 *
 * The [payload] is also a JSON object, which when encoded, is turned into a JSON [String] and Base64
 * URL encoded.
 *
 * The Signature is a result of taking the encoded Header and Payload, adding a period character
 * between them, and signing that, along with a secret key, using the signing algorithm specified
 * in the Header. For example:
 *
 * ```
 * val signature = HMACSHA256(
 *     base64UrlEncode(header) + "." +
 *     base64UrlEncode(payload),
 *     secret)
 * ```
 *
 * This interface represents just the decoded [header] and [payload] of a JWT, and not the Signature
 * which is obtained after the other values are already encoded. For an extension of this
 * interface, which includes the Signature, refer to the [Jws] interface.
 *
 * To obtain an instance of this interface, use the [Jwt] constructor function.
 *
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519)
 * @see [jwt.io](https://jwt.io/introduction/)
 * @see [Jws]
 */
@ExperimentalJwtApi
public interface Jwt : Compactable,
    Signable {

    /**
     * The [Header] of this [Jwt] token.
     */
    public val header: Header

    /**
     * The [Claims] body of this [Jwt] token.
     */
    public val payload: Claims

    /**
     * A builder component for creating a [Jwt] instance.
     */
    public abstract class Builder {

        public abstract fun header(builder: Header.Builder.() -> Unit)

        public abstract fun payload(builder: JsonClaims.Builder.() -> Unit)

        public abstract fun payload(claims: TextClaims)
    }

    public fun interface Generator {

        public suspend fun generate(
            json: Json,
            builder: Builder.() -> Unit
        ): Jwt
    }

    public fun interface Validator {

        @Throws(JwtValidationException::class, CancellationException::class)
        public suspend fun validate(
            compacted: CompactedJwt,
            json: Json,
            validation: suspend Jwt.() -> Boolean
        ): Jwt
    }

    public fun interface Parser {

        @Throws(JwtParseException::class, CancellationException::class)
        public suspend fun parse(
            compacted: CompactedJwt,
            json: Json
        ): Jwt
    }

    public companion object : Generator by DefaultJwtGenerator,
        Validator by DefaultJwtValidator,
        Parser by DefaultJwtParser
}

@ExperimentalJwtApi
internal expect val DefaultJwtGenerator: Jwt.Generator

@ExperimentalJwtApi
internal expect val DefaultJwtParser: Jwt.Parser

@ExperimentalJwtApi
internal val DefaultJwtValidator: Jwt.Validator =
    Jwt.Validator { compacted, json, validation ->
        val jws = try {
            Jwt.parse(
                compacted = compacted,
                json = json
            )
        } catch (e: JwtParseException) {
            throw JwtValidationException(
                message = "Error parsing JWT for validation.",
                cause = e
            )
        }

        val result = validation.invoke(jws)

        if (!result) {
            throw JwtValidationException(message = "Validation failed for JWT $jws.")
        }

        jws
    }
