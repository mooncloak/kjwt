package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Represents a JSON Web Signature (JWS), along with the decoded JSON Web Token (JWT) properties.
 * This is an extension on the [Jwt] interface but provides the [signature] value.
 *
 * The Signature is a result of taking the encoded [Jwt.header] and [Jwt.payload], adding a period
 * character between them, and signing that, along with a secret key, using the signing algorithm
 * specified in the Header. For example:
 *
 * ```
 * val signature = HMACSHA256(
 *     base64UrlEncode(header) + "." +
 *     base64UrlEncode(payload),
 *     secret)
 * ```
 *
 * A [Jws] provides integrity protection for a [Jwt]. Because the Header and Payload of the JWT are
 * signed by the creator of the token, it can easily be verified to make sure that the data was not
 * tampered with. The signature algorithm can practically be any cryptographic signature algorithm,
 * symmetric or asymmetric, but the natively supported options are defined by the
 * [SignatureAlgorithm] enum class.
 *
 * A JWS takes the following encoded form:
 *
 * ```
 * Base64URL-Encoded-Header.Base64URL-Encoded-Payload.Base64URL-Encoded-Signature
 * ```
 *
 * In the absence of a signature (an "unsecured" JWT, see [Jws.isUnsecured]), a JWS takes the
 * following encoded form:
 *
 * ```
 * Base64URL-Encoded-Header.Base64URL-Encoded-Payload.
 * ```
 *
 * @see [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515)
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519)
 * @see [Jwt]
 */
@ExperimentalJwtApi
public interface Jws : Jwt,
    Compactable {

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

/**
 * Determines whether this [Jws] is considered an "unsecured" JWT, which, according to the
 * [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-6), is a "JWS using
 * the "alg" Header Parameter value "none" and with the empty string for its JWS Signature value,
 * as defined in the JWA specification[.]"
 *
 * @see [Jws.isSecured] for the inverse operation.
 * @see [Unsecured JWTs](https://datatracker.ietf.org/doc/html/rfc7519#section-6)
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519)
 * @see [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515)
 * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html)
 * @see [Jws]
 */
@ExperimentalJwtApi
public val Jws.isUnsecured: Boolean
    get() = this.header.signatureAlgorithm == SignatureAlgorithm.NONE
            && this.signature.value.isEmpty()

/**
 * Determines whether this [Jws] is considered "secured" with a signature, which is the inverse of
 * the [isUnsecured] value.
 *
 * @see [isUnsecured] for more information.
 */
@ExperimentalJwtApi
public val Jws.isSecured: Boolean
    get() = !isUnsecured

@ExperimentalJwtApi
internal data object DefaultJwsValidator : Jws.Validator {

    override suspend fun validate(
        compacted: CompactedJwt,
        json: Json,
        resolver: KeyResolver,
        validation: suspend Jws.() -> Boolean
    ): Jws {
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

        return jws
    }
}

@ExperimentalJwtApi
internal data object DefaultJwsParser : Jws.Parser {

    @ExperimentalKeyApi
    override suspend fun parse(compacted: CompactedJwt, json: Json, resolver: KeyResolver): Jws {
        // The parsing and validation of a JWS is defined by the following specifications:
        // https://datatracker.ietf.org/doc/html/rfc7519#section-7.2
        // https://datatracker.ietf.org/doc/html/rfc7515#section-5.2

        if (!compacted.value.contains('.')) {
            throw JwtParseException("Compacted JWT must contain at least one period ('.') character.")
        }

        // Obtain the different sections of the compacted JWT, delimited by the period ('.')
        // character. We first trim whitespace from the compacted JWT String value to be lenient,
        // as whitespace is not allowed for any section of the JWT.
        val sections = compacted.value.trim().split('.')

        // According to the JWE specification (mentioned in the JWT specification), a JWS can be
        // distinguished by a JWE by the following criteria:
        // - JWSs have three segments separated by two period ('.') characters.
        // - JWEs have five segments separated by four period ('.') characters.
        // https://www.rfc-editor.org/rfc/rfc7516.html#section-9
        //
        // As well as the mentioned above criteria, we also support two segments separated by a
        // single period ('.') character for an unsecured JWS. This is technically a malformed JWS,
        // but is identical to an "unsecured" JWS with a trailing period character, for our
        // use-cases.
        return when (sections.size) {
            2, 3 -> parseJws(
                json = json,
                resolver = resolver,
                sections = sections
            )

            5 -> throw JwtParseException("JWEs are not currently supported.")

            else -> throw JwtParseException("Invalid JWT structure. Incorrect number of segments: '${sections.size}'.")
        }
    }

    @ExperimentalKeyApi
    @OptIn(ExperimentalEncodingApi::class)
    private suspend fun parseJws(
        json: Json,
        resolver: KeyResolver,
        sections: List<String>
    ): Jws {
        val headerSection = sections.firstOrNull()
            ?: throw JwtParseException("Compacted JWS must contain a header section.")

        // According to the JWT specification, no line breaks, whitespace, or other additional
        // characters are allowed in the header section (Section 7.2, Item 3):
        // https://datatracker.ietf.org/doc/html/rfc7519#section-7.2
        if (headerSection.any { char -> char.isWhitespace() || char == '\n' }) {
            throw JwtParseException("No whitespace or line break characters are allowed in a compacted JWT section.")
        }

        val headerJsonString = Base64.UrlSafe.decode(headerSection).decodeToString()
        val header = json.decodeFromString(
            deserializer = Header.serializer(),
            string = headerJsonString
        )

        val payloadSection = sections.getOrNull(1)
            ?: throw JwtParseException("Compacted JWS must contain a payload section.")
        val payloadJsonString = Base64.UrlSafe.decode(payloadSection).decodeToString()
        val payload = json.decodeFromString(
            deserializer = JsonClaims.serializer(),
            string = payloadJsonString
        )

        val signatureSection = sections.getOrNull(2)

        if (signatureSection.isNullOrEmpty()) {
            if (header.signatureAlgorithm != SignatureAlgorithm.NONE) {
                throw JwtParseException("Compacted JWS did NOT contain a signature and the signature algorithm was NOT set to none.")
            }

            return DefaultJws(
                header = header,
                payload = payload,
                signature = Signature(value = ""),
                json = json
            )
        }

        val decodedSignature = Base64.UrlSafe.decode(signatureSection).decodeToString()

        val key = resolver.resolve(header)

        val signatureInput = "$headerSection.$payloadSection"
        val signature = sign(
            input = signatureInput,
            key = key,
            algorithm = header.signatureAlgorithm
                ?: throw UnsupportedJwtSignatureAlgorithm("Missing or unsupported signature algorithm used in JWS header: '${header.signatureAlgorithm?.serialName}'.")
        )

        if (signature.value != decodedSignature) {
            throw JwtParseException("Signature of JWS was invalid.")
        }

        return DefaultJws(
            header = header,
            payload = payload,
            signature = signature,
            json = json
        )
    }
}

@ExperimentalJwtApi
internal class DefaultJws internal constructor(
    override val header: Header,
    override val payload: Claims,
    override val signature: Signature,
    private val json: Json
) : Jws {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun compact(): CompactedJwt {
        // The creation of compacted JWT are defined by the JWT specification:
        // https://datatracker.ietf.org/doc/html/rfc7519#section-7.1
        val claimString = when (payload) {
            is TextClaims -> payload.value
            is JsonClaims -> json.encodeToString(
                serializer = JsonObject.serializer(),
                value = payload.toJsonObject()
            )
        }
        val encodedPayload = Base64.UrlSafe.encode(claimString.encodeToByteArray())
        val headerString = json.encodeToString(
            serializer = JsonObject.serializer(),
            value = header.toJsonObject()
        )
        val encodedHeader = Base64.UrlSafe.encode(headerString.encodeToByteArray())
        val encodedSignature = Base64.UrlSafe.encode(signature.value.encodeToByteArray())

        val compactedString = "$encodedHeader.$encodedPayload.$encodedSignature"

        return CompactedJwt(value = compactedString)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DefaultJws) return false

        if (header != other.header) return false
        if (payload != other.payload) return false
        if (signature != other.signature) return false

        return json == other.json
    }

    override fun hashCode(): Int {
        var result = header.hashCode()
        result = 31 * result + payload.hashCode()
        result = 31 * result + signature.hashCode()
        result = 31 * result + json.hashCode()
        return result
    }

    override fun toString(): String =
        "DefaultJws(header=$header, payload=$payload, signature=$signature, json=$json)"
}
