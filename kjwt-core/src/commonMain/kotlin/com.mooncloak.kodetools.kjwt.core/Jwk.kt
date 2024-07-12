package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

/**
 * An interface that represents a JWK (JSON Web Key).
 *
 * To construct an instance of this class, use one of the [Jwk] constructor functions.
 *
 * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517)
 */
@ExperimentalJwtApi
@Serializable(with = JwkSerializer::class)
public class Jwk public constructor(
    override val json: Json,
    override val properties: Map<String, JsonElement>,
    /**
     * The "kty" (key type) parameter identifies the cryptographic algorithm family used with the
     * key, such as "RSA" or "EC". "kty" values should either be registered in the IANA "JSON Web
     * Key Types" registry established by
     * [JWA](https://datatracker.ietf.org/doc/html/rfc7518) or be a value that contains a
     * Collision-Resistant Name. The "kty" value is a case-sensitive string. This member MUST be
     * present in a JWK.
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.1)
     */
    public val keyType: KeyType
) : JwtObject() {

    /**
     * The "use" (public key use) parameter identifies the intended use of the public key. The
     * "use" parameter is employed to indicate whether a public key is used for encrypting data or
     * verifying the signature on data.
     *
     * Values defined by this specification are:
     * * "sig" (signature)
     * * "enc" (encryption)
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.2)
     */
    public val use: KeyUse? by property(key = PropertyKey.USE)

    /**
     * The "key_ops" (key operations) parameter identifies the operation(s) for which the key is
     * intended to be used. The "key_ops" parameter is intended for use cases in which public,
     * private, or symmetric keys may be present.
     *
     * Its value is an array of key operation values. Values defined by this specification are:
     *
     * * "sign" (compute digital signature or MAC)
     * * "verify" (verify digital signature or MAC)
     * * "encrypt" (encrypt content)
     * * "decrypt" (decrypt content and validate decryption, if applicable)
     * * "wrapKey" (encrypt key)
     * * "unwrapKey" (decrypt key and validate decryption, if applicable)
     * * "deriveKey" (derive key)
     * * "deriveBits" (derive bits not to be used as a key)
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.3)
     */
    public val keyOperations: List<KeyOperation>? by property(key = PropertyKey.KEY_OPERATIONS)

    /**
     * The "alg" (algorithm) parameter identifies the algorithm intended for use with the key.
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.4)
     */
    public val algorithm: String? by property(key = PropertyKey.ALGORITHM)

    /**
     * The "kid" (key ID) parameter is used to match a specific key. This is used, for instance, to
     * choose among a set of keys within a JWK Set during key rollover.
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.5)
     */
    public val keyId: String? by property(key = PropertyKey.KEY_ID)

    /**
     * The "x5u" (X.509 URL) parameter is a URI [RFC3986] that refers to a resource for an X.509
     * public key certificate or certificate chain [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.6)
     */
    public val x5u: String? by property(key = PropertyKey.X5U)

    /**
     * The "x5c" (X.509 certificate chain) parameter contains a chain of one or more PKIX
     * certificates [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.7)
     */
    public val x5c: String? by property(key = PropertyKey.X5C)

    /**
     * The "x5t" (X.509 certificate SHA-1 thumbprint) parameter is a base64url-encoded SHA-1
     * thumbprint (a.k.a. digest) of the DER encoding of an X.509 certificate [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.8)
     */
    public val x5t: String? by property(key = PropertyKey.X5T)

    /**
     * The "x5t#S256" (X.509 certificate SHA-256 thumbprint) parameter is a base64url-encoded
     * SHA-256 thumbprint (a.k.a. digest) of the DER encoding of an X.509 certificate [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.9)
     */
    public val x5tS256: String? by property(key = PropertyKey.X5T_S256)

    /**
     * Jwk key values. This consists of the keys for the standard JWK properties, but other keys
     * can be added via extension properties and functions.
     */
    public object PropertyKey {

        /**
         * The key for the [Jwk.keyType] property. This constant value is equal to "kty".
         */
        public const val KEY_TYPE: String = "kty"

        /**
         * The key for the [Jwk.use] property. This constant value is equal to "use".
         */
        public const val USE: String = "use"

        /**
         * The key for the [Jwk.keyOperations] property. This constant value is equal to "key_ops".
         */
        public const val KEY_OPERATIONS: String = "key_ops"

        /**
         * The key for the [Jwk.algorithm] property. This constant value is equal to "alg".
         */
        public const val ALGORITHM: String = "alg"

        /**
         * The key for the [Jwk.keyId] property. This constant value is equal to "kid".
         */
        public const val KEY_ID: String = "kid"

        /**
         * The key for the [Jwk.x5u] property. This constant value is equal to "x5u".
         */
        public const val X5U: String = "x5u"

        /**
         * The key for the [Jwk.x5c] property. This constant value is equal to "x5c".
         */
        public const val X5C: String = "x5c"

        /**
         * The key for the [Jwk.x5t] property. This constant value is equal to "x5t".
         */
        public const val X5T: String = "x5t"

        /**
         * The key for the [Jwk.x5tS256] property. This constant value is equal to "x5t#S256".
         */
        public const val X5T_S256: String = "x5t#S256"
    }

    /**
     * A builder component for creating a [Jwk] instance. This component should not be created
     * directly, but instead can be used to create a [Jwk] instance via the [Jwk] constructor
     * function.
     */
    public class Builder internal constructor(
        initialKeyType: KeyType,
        override val json: Json,
        initialValues: Map<String, JsonElement> = emptyMap()
    ) : JwtObject.Builder(initialValues) {

        /**
         * Gets/sets the [Jwk.keyType] value.
         */
        public var keyType: KeyType by property(
            key = PropertyKey.KEY_TYPE,
            initialValue = initialKeyType
        )

        /**
         * Gets/sets the [Jwk.use] value.
         */
        public var use: KeyUse? by property(key = PropertyKey.USE)

        /**
         * Gets/sets the [Jwk.keyOperations] value.
         */
        public var keyOperations: List<KeyOperation>? by property(key = PropertyKey.KEY_OPERATIONS)

        /**
         * Gets/sets the [Jwk.algorithm] value.
         */
        public var algorithm: String? by property(key = PropertyKey.ALGORITHM)

        /**
         * Gets/sets the [Jwk.algorithm] value.
         */
        public var keyId: String? by property(key = PropertyKey.KEY_ID)

        /**
         * Gets/sets the [Jwk.x5u] value.
         */
        public var x5u: String? by property(key = PropertyKey.X5U)

        /**
         * Gets/sets the [Jwk.x5t] value.
         */
        public var x5t: String? by property(key = PropertyKey.X5T)

        /**
         * Gets/sets the [Jwk.x5c] value.
         */
        public var x5c: String? by property(key = PropertyKey.X5C)

        /**
         * Gets/sets the [Jwk.x5tS256] value.
         */
        public var x5tS256: String? by property(key = PropertyKey.X5T_S256)

        /**
         * Converts this [Jwk.Builder] instance into a [Jwk] instance.
         */
        public fun build(): Jwk =
            Jwk(
                json = json,
                properties = properties,
                keyType = keyType
            )
    }

    public companion object
}

/**
 * Converts this [Jwk] instance into a [Jwk.Builder].
 */
@ExperimentalJwtApi
public fun Jwk.toBuilder(): Jwk.Builder =
    Jwk.Builder(
        json = this.json,
        initialValues = this.properties,
        initialKeyType = this.keyType
    )

/**
 * Creates a new [Jwk] instance starting with the same values from this [Jwk] instance which can be
 * overridden from the provided builder [block].
 */
@ExperimentalJwtApi
public fun Jwk.copy(block: Jwk.Builder.() -> Unit = {}): Jwk {
    val builder = this.toBuilder().apply(block)

    return builder.build()
}

/**
 * The [KSerializer] implementation for the [Jwk] class.
 */
@ExperimentalJwtApi
internal object JwkSerializer : BaseJwtObjectSerializer<Jwk>() {

    override fun toType(json: Json, jsonObject: JsonObject): Jwk {
        val keyType = jsonObject[Jwk.PropertyKey.KEY_TYPE]
            ?.jsonPrimitive
            ?.contentOrNull
            ?.let {
                KeyType(value = it)
            }
            ?: error("Jwk must have a Key Type property.")

        return Jwk(
            json = json,
            properties = jsonObject,
            keyType = keyType
        )
    }
}
