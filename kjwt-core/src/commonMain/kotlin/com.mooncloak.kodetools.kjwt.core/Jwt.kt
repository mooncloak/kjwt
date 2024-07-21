package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.Jwt.Builder
import com.mooncloak.kodetools.kjwt.core.signature.Default
import com.mooncloak.kodetools.kjwt.core.signature.Signable
import com.mooncloak.kodetools.kjwt.core.signature.Signature
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.signature.Signer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Represents a decoded JSON Web Token (JWT) without the Signature defined in a [Jws]. According to
 * the [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-3), a JWT is
 * either a [Jws] or a JWE.
 *
 * The [header] is a JSON object, which when encoded it is turned into a JSON [String] and Base64
 * URL encoded.
 *
 * The [payload] is also a JSON object, which when encoded, is turned into a JSON [String] and
 * Base64 URL encoded.
 *
 * This interface represents just the decoded [header] and [payload] of a JWT, and not the
 * Signature of a [Jws], which is obtained after the other values are
 * already encoded, or the extra parts of a JWE. For an extension of this interface, which includes
 * the Signature, refer to the [Jws] interface.
 *
 * To obtain an instance of this interface, use the [Jwt] constructor function.
 *
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519)
 * @see [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515)
 * @see [jwt.io](https://jwt.io/introduction/)
 * @see [Jws]
 */
@ExperimentalJwtApi
public interface Jwt {

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
    @ExperimentalJwtApi
    public class Builder internal constructor(
        private val json: Json,
        private var headerValue: Header? = null,
        private var claimsValue: Claims? = null
    ) {

        public fun header(builder: Header.Builder.() -> Unit) {
            headerValue = Header.Builder(json = json).apply(builder).build()
        }

        public fun payload(builder: JsonClaims.Builder.() -> Unit) {
            claimsValue = JsonClaims.Builder(json = json).apply(builder).build()
        }

        public fun payload(claims: TextClaims) {
            claimsValue = claims
        }

        internal fun build(): UnsignedJwt {
            requireNotNull(this.headerValue) { "Cannot create a JWT. Header value is not initialized." }
            requireNotNull(this.claimsValue) { "Cannot create a JWT. Claims value is not initialized." }

            return UnsignedJwt(
                header = headerValue!!,
                payload = claimsValue!!,
                json = json
            )
        }
    }

    public companion object
}

@ExperimentalJwtApi
public class UnsignedJwt internal constructor(
    override val header: Header,
    override val payload: Claims,
    private val json: Json,
    private val signer: Signer = Signer.Default
) : Jwt,
    Signable {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun sign(key: Jwk?, algorithm: SignatureAlgorithm): Jws {
        if (key == null && algorithm != SignatureAlgorithm.NONE) {
            throw UnsupportedJwtSignatureAlgorithm("Signature algorithm '${algorithm.serialName}' requires a key but `null` was provided.")
        }

        if (key == null) {
            return unsecured()
        }

        // The creation of compacted JWT are defined by the JWT specification:
        // https://datatracker.ietf.org/doc/html/rfc7519#section-7.1
        val claimString = when (payload) {
            is TextClaims -> error("Illegal claims used for a JWS. A JWS must use JsonClaims.")
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

        val signatureInput = "$encodedHeader.$encodedPayload"
        val signature = signer.sign(
            input = signatureInput,
            key = key,
            algorithm = algorithm
        )

        return DefaultJws(
            header = header,
            payload = payload,
            signature = signature,
            json = json
        )
    }

    /**
     * Creates an "unsecured" [Jws] instance. Note that this requires the
     * [Header.signatureAlgorithm] value to be equal to [SignatureAlgorithm.NONE], otherwise an
     * [IllegalStateException] will be thrown.
     *
     * @throws [IllegalStateException] if this [UnsignedJwt.header]'s [Header.signatureAlgorithm]
     * is NOT [SignatureAlgorithm.NONE].
     *
     * @see [Jws.isUnsecured]
     *
     * @return An "unsecured" [Jws], meaning that the signature value is empty.
     */
    @Throws(IllegalStateException::class)
    public fun unsecured(): Jws =
        DefaultJws(
            header = header,
            payload = payload,
            signature = Signature(value = ""),
            json = json
        )
}

@ExperimentalJwtApi
public operator fun Jwt.Companion.invoke(
    json: Json = Json.Default,
    builder: Builder.() -> Unit
): UnsignedJwt =
    Builder(json = json)
        .apply(builder)
        .build()

@ExperimentalJwtApi
public fun Jwt.Companion.from(
    header: Header,
    payload: Claims,
    json: Json = Json.Default
): UnsignedJwt =
    UnsignedJwt(
        header = header,
        payload = payload,
        json = json
    )
