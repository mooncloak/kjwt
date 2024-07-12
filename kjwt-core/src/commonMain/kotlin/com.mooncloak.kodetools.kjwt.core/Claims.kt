package com.mooncloak.kodetools.kjwt.core

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlin.jvm.JvmInline

@ExperimentalJwtApi
public sealed interface Claims

@ExperimentalJwtApi
@JvmInline
@Serializable
public value class TextClaims public constructor(
    public val value: String
) : Claims

/**
 * An interface that represents a JWT body's payload claims.
 *
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1)
 */
@ExperimentalJwtApi
@Serializable(with = JsonClaimsSerializer::class)
public class JsonClaims public constructor(
    override val json: Json,
    override val properties: Map<String, JsonElement>
) : Claims,
    JwtObject() {

    /**
     * The "iss" (issuer) claim identifies the principal that issued the JWT.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.1)
     */
    public val issuer: String? by property(PropertyKey.ISSUER)

    /**
     * The "sub" (subject) claim identifies the principal that is the subject of the JWT.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.2)
     */
    public val subject: String? by property(PropertyKey.SUBJECT)

    /**
     * The "aud" (audience) claim identifies the recipients that the JWT is intended for.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.3)
     */
    public val audience: Set<String>? by property(PropertyKey.AUDIENCE)

    /**
     * The "exp" (expiration time) claim identifies the expiration time on or after which the JWT
     * MUST NOT be accepted for processing. This value should be a "Numeric Date" which is a JSON
     * number value representing the **seconds** since the Epoch.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.4)
     * @see [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2)
     */
    public val expiration: Instant? by property(
        key = PropertyKey.EXPIRATION,
        deserializer = SecondsSinceEpochSerializer
    )

    /**
     * The "nbf" (not before) claim identifies the time before which the JWT MUST NOT be accepted
     * for processing. This value should be a "Numeric Date" which is a JSON number value
     * representing the **seconds** since the Epoch.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.5)
     * @see [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2)
     */
    public val notBefore: Instant? by property(
        key = PropertyKey.NOT_BEFORE,
        deserializer = SecondsSinceEpochSerializer
    )

    /**
     * The "iat" (issued at) claim identifies the time at which the JWT was issued. This value
     * should be a "Numeric Date" which is a JSON number value representing the **seconds** since
     * the Epoch.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.6)
     * @see [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2)
     */
    public val issuedAt: Instant? by property(
        key = PropertyKey.ISSUED_AT,
        deserializer = SecondsSinceEpochSerializer
    )

    /**
     * The "jti" (JWT ID) claim provides a unique identifier for the JWT.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.7)
     */
    public val id: String? by property(PropertyKey.ID)

    /**
     * Claim key values. This consists of the keys for the standard claim values, but other keys
     * can be added via extension properties and functions.
     */
    public object PropertyKey {

        /**
         * The key for the [JsonClaims.id] property. This constant value is equal to "jti".
         */
        public const val ID: String = "jti"

        /**
         * The key for the [JsonClaims.issuer] property. This constant value is equal to "iss".
         */
        public const val ISSUER: String = "iss"

        /**
         * The key for the [JsonClaims.subject] property. This constant value is equal to "sub".
         */
        public const val SUBJECT: String = "sub"

        /**
         * The key for the [JsonClaims.audience] property. This constant value is equal to "aud".
         */
        public const val AUDIENCE: String = "aud"

        /**
         * The key for the [JsonClaims.expiration] property. This constant value is equal to "exp".
         */
        public const val EXPIRATION: String = "exp"

        /**
         * The key for the [JsonClaims.notBefore] property. This constant value is equal to "nbf".
         */
        public const val NOT_BEFORE: String = "nbf"

        /**
         * The key for the [JsonClaims.issuedAt] property. This constant value is equal to "iat".
         */
        public const val ISSUED_AT: String = "iat"
    }

    /**
     * A builder component for creating a [JsonClaims] instance. This component should not be
     * created directly, but instead can be used to create a [JsonClaims] instance via the
     * [JsonClaims] constructor function.
     */
    public class Builder internal constructor(
        override val json: Json,
        initialValues: Map<String, JsonElement> = emptyMap()
    ) : JwtObject.Builder(initialValues) {

        /**
         * Gets/sets the [JsonClaims.id] value.
         */
        public var id: String? by property(key = PropertyKey.ID)

        /**
         * Gets/sets the [JsonClaims.issuer] value.
         */
        public var issuer: String? by property(key = PropertyKey.ISSUER)

        /**
         * Gets/sets the [JsonClaims.subject] value.
         */
        public var subject: String? by property(key = PropertyKey.SUBJECT)

        /**
         * Gets/sets the [JsonClaims.audience] value.
         */
        public var audience: Set<String>? by property(key = PropertyKey.AUDIENCE)

        /**
         * Gets/sets the [JsonClaims.expiration] value.
         */
        public var expiration: Instant? by property(
            key = PropertyKey.EXPIRATION,
            serializer = SecondsSinceEpochSerializer,
            deserializer = SecondsSinceEpochSerializer
        )

        /**
         * Gets/sets the [JsonClaims.notBefore] value.
         */
        public var notBefore: Instant? by property(
            key = PropertyKey.NOT_BEFORE,
            serializer = SecondsSinceEpochSerializer,
            deserializer = SecondsSinceEpochSerializer
        )

        /**
         * Gets/sets the [JsonClaims.issuedAt] value.
         */
        public var issuedAt: Instant? by property(
            key = PropertyKey.ISSUED_AT,
            serializer = SecondsSinceEpochSerializer,
            deserializer = SecondsSinceEpochSerializer
        )

        /**
         * Creates a [JsonClaims] instance from this [JsonClaims.Builder].
         */
        public fun build(): JsonClaims = JsonClaims(
            json = json,
            properties = properties
        )
    }

    public companion object
}

/**
 * Converts this [JsonClaims] instance into a [JsonClaims.Builder].
 */
@ExperimentalJwtApi
public fun JsonClaims.toBuilder(): JsonClaims.Builder =
    JsonClaims.Builder(
        json = this.json,
        initialValues = this.properties
    )

/**
 * Creates a new [JsonClaims] instance starting with the same values from this [JsonClaims]
 * instance which can be overridden from the provided builder [block].
 */
@ExperimentalJwtApi
public fun JsonClaims.copy(block: JsonClaims.Builder.() -> Unit = {}): JsonClaims {
    val builder = this.toBuilder().apply(block)

    return builder.build()
}

/**
 * Creates a [JsonClaims] from the provided builder [block] and [json] instance.
 */
@ExperimentalJwtApi
public fun JsonClaims.Companion.build(
    json: Json,
    block: JsonClaims.Builder.() -> Unit
): JsonClaims =
    JsonClaims.Builder(json = json).apply(block).build()

@ExperimentalJwtApi
internal class JsonClaimsSerializer internal constructor() : BaseJwtObjectSerializer<JsonClaims>() {

    override fun toType(json: Json, jsonObject: JsonObject): JsonClaims =
        JsonClaims(
            json = json,
            properties = jsonObject
        )
}
