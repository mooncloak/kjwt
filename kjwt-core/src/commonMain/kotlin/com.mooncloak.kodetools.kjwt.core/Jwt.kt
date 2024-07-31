package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.Jwt.Builder
import com.mooncloak.kodetools.kjwt.core.key.KeyOperation
import com.mooncloak.kodetools.kjwt.core.key.KeyResolver
import com.mooncloak.kodetools.kjwt.core.signature.Default
import com.mooncloak.kodetools.kjwt.core.signature.Signable
import com.mooncloak.kodetools.kjwt.core.signature.Signature
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.signature.SignatureInput
import com.mooncloak.kodetools.kjwt.core.signature.Signer
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
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
        private val signer: Signer = Signer.Default,
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
                json = json,
                signer = signer
            )
        }
    }

    public companion object
}

/**
 * Obtains the [Jwt.header] property in a deconstructed manner.
 */
@ExperimentalJwtApi
public operator fun Jwt.component1(): Header = header

/**
 * Obtains the [Jwt.payload] property in a deconstructed manner.
 */
@ExperimentalJwtApi
public operator fun Jwt.component2(): Claims = payload

@ExperimentalJwtApi
public class UnsignedJwt internal constructor(
    override val header: Header,
    override val payload: Claims,
    internal val json: Json,
    internal val signer: Signer
) : Jwt,
    Signable {

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun sign(resolver: KeyResolver, algorithm: SignatureAlgorithm): Jws {
        val key = resolver.resolve(
            header = header,
            operation = KeyOperation.Sign
        )

        if (key == null && algorithm != SignatureAlgorithm.NONE) {
            throw UnsupportedJwtSignatureAlgorithm("Signature algorithm '${algorithm.serialName}' requires a key but `null` was provided.")
        } else if (key == null) {
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

        val signatureInput = SignatureInput(value = "$encodedHeader.$encodedPayload")
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
            signature = Signature.Empty,
            json = json
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UnsignedJwt) return false

        if (header != other.header) return false
        if (payload != other.payload) return false
        if (json != other.json) return false
        if (signer != other.signer) return false

        return signer == other.signer
    }

    override fun hashCode(): Int {
        var result = header.hashCode()
        result = 31 * result + payload.hashCode()
        result = 31 * result + json.hashCode()
        result = 31 * result + signer.hashCode()
        return result
    }

    override fun toString(): String =
        "UnsignedJwt(header=$header, payload=$payload, json=$json, signer=$signer)"
}

/**
 * Creates an [UnsignedJwt] instance from the provided [builder].
 *
 * @param [json] The [Json] instance to use for serialization and deserialization.
 *
 * @param [signer] The [Signer] to use for creating the [Jwt] [Signature].
 *
 * @param [builder] The [Jwt.Builder] scoped function used to build an [UnsignedJwt].
 */
@ExperimentalJwtApi
public fun Jwt.Companion.build(
    json: Json = Json.Default,
    signer: Signer = Signer.Default,
    builder: Builder.() -> Unit
): UnsignedJwt = Builder(
    json = json,
    signer = signer
).apply(builder)
    .build()

/**
 * Creates an [UnsignedJwt] instance from the provided [builder]. This is a convenience function
 * for invoking the [build] function.
 *
 * @param [json] The [Json] instance to use for serialization and deserialization.
 *
 * @param [signer] The [Signer] to use for creating the [Jwt] [Signature].
 *
 * @param [builder] The [Jwt.Builder] scoped function used to build an [UnsignedJwt].
 */
@ExperimentalJwtApi
public operator fun Jwt.Companion.invoke(
    json: Json = Json.Default,
    signer: Signer = Signer.Default,
    builder: Builder.() -> Unit
): UnsignedJwt = build(
    json = json,
    signer = signer,
    builder = builder
)

/**
 * Creates an [UnsignedJwt] instance from the provided values.
 *
 * @param [header] The [Jwt] [Header].
 *
 * @param [payload] The [Jwt] payload.
 *
 * @param [json] The [Json] instance to use for serialization and deserialization.
 *
 * @param [signer] The [Signer] to use for creating the [Jwt] [Signature].
 */
@ExperimentalJwtApi
public fun Jwt.Companion.from(
    header: Header,
    payload: Claims,
    json: Json = Json.Default,
    signer: Signer = Signer.Default
): UnsignedJwt =
    UnsignedJwt(
        header = header,
        payload = payload,
        json = json,
        signer = signer
    )

/**
 * Converts this [UnsignedJwt] instance into a [Jwt.Builder].
 */
@ExperimentalJwtApi
public fun UnsignedJwt.toBuilder(): Builder =
    Builder(
        json = this.json,
        signer = this.signer,
        headerValue = this.header,
        claimsValue = this.payload
    )

/**
 * Creates a new [UnsignedJwt] instance starting with the same values from this [UnsignedJwt]
 * instance which can be overridden from the provided builder [block].
 */
@ExperimentalJwtApi
public fun UnsignedJwt.copy(block: Jwt.Builder.() -> Unit = {}): UnsignedJwt {
    val builder = this.toBuilder().apply(block)

    return builder.build()
}
