package com.mooncloak.kodetools.kjwt.core

import kotlinx.datetime.Instant
import kotlinx.serialization.json.*
import kotlin.jvm.JvmInline

@ExperimentalJwtApi
public sealed interface Claims

@ExperimentalJwtApi
@JvmInline
public value class TextClaims public constructor(
    public val value: String
) : Claims

/**
 * An interface that represents a JWT body's payload claims.
 *
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1)
 */
@ExperimentalJwtApi
public interface JsonClaims : Claims,
    JwtObject {

    /**
     * The "iss" (issuer) claim identifies the principal that issued the JWT.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.1)
     */
    public val issuer: String?

    /**
     * The "sub" (subject) claim identifies the principal that is the subject of the JWT.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.2)
     */
    public val subject: String?

    /**
     * The "aud" (audience) claim identifies the recipients that the JWT is intended for.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.3)
     */
    public val audience: Set<String>?

    /**
     * The "exp" (expiration time) claim identifies the expiration time on or after which the JWT MUST NOT be accepted
     * for processing. This value should be a "Numeric Date" which is a JSON number value representing the
     * **seconds** since the Epoch.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.4)
     * @see [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2)
     */
    public val expiration: Instant?

    /**
     * The "nbf" (not before) claim identifies the time before which the JWT MUST NOT be accepted for processing. This
     * value should be a "Numeric Date" which is a JSON number value representing the **seconds** since the Epoch.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.5)
     * @see [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2)
     */
    public val notBefore: Instant?

    /**
     * The "iat" (issued at) claim identifies the time at which the JWT was issued. This value should be a
     * "Numeric Date" which is a JSON number value representing the **seconds** since the Epoch.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.6)
     * @see [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2)
     */
    public val issuedAt: Instant?

    /**
     * The "jti" (JWT ID) claim provides a unique identifier for the JWT.
     *
     * Value defaults to `null`.
     *
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.7)
     */
    public val id: String?

    /**
     * Claim key values. This consists of the keys for the standard claim values, but other keys can be added via
     * extension properties and functions.
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
     * A builder component for creating a [JsonClaims] instance. This component should not be created directly, but instead
     * can be used to create a [JsonClaims] instance via the [JsonClaims] constructor function.
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
        public var expiration: Instant? by property(key = PropertyKey.EXPIRATION)

        /**
         * Gets/sets the [JsonClaims.notBefore] value.
         */
        public var notBefore: Instant? by property(key = PropertyKey.NOT_BEFORE)

        /**
         * Gets/sets the [JsonClaims.issuedAt] value.
         */
        public var issuedAt: Instant? by property(key = PropertyKey.ISSUED_AT)
    }

    public companion object
}
