package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
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
public sealed interface Jwk : JwtObject {

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

    /**
     * The "use" (public key use) parameter identifies the intended use of the public key. The "use" parameter is
     * employed to indicate whether a public key is used for encrypting data or verifying the signature on data.
     *
     * Values defined by this specification are:
     * * "sig" (signature)
     * * "enc" (encryption)
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.2)
     */
    public val use: KeyUse?

    /**
     * The "key_ops" (key operations) parameter identifies the operation(s) for which the key is intended to be used.
     * The "key_ops" parameter is intended for use cases in which public, private, or symmetric keys may be present.
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
    public val keyOperations: List<KeyOperation>?

    /**
     * The "alg" (algorithm) parameter identifies the algorithm intended for use with the key.
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.4)
     */
    public val algorithm: String?

    /**
     * The "kid" (key ID) parameter is used to match a specific key. This is used, for instance, to choose among a set
     * of keys within a JWK Set during key rollover.
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.5)
     */
    public val keyId: String?

    /**
     * The "x5u" (X.509 URL) parameter is a URI [RFC3986] that refers to a resource for an X.509 public key certificate
     * or certificate chain [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.6)
     */
    public val x5u: String?

    /**
     * The "x5c" (X.509 certificate chain) parameter contains a chain of one or more PKIX certificates [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.7)
     */
    public val x5c: String?

    /**
     * The "x5t" (X.509 certificate SHA-1 thumbprint) parameter is a base64url-encoded SHA-1 thumbprint (a.k.a. digest)
     * of the DER encoding of an X.509 certificate [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.8)
     */
    public val x5t: String?

    /**
     * The "x5t#S256" (X.509 certificate SHA-256 thumbprint) parameter is a base64url-encoded SHA-256 thumbprint
     * (a.k.a. digest) of the DER encoding of an X.509 certificate [RFC5280].
     *
     * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.9)
     */
    public val x5tS256: String?

    /**
     * Jwk key values. This consists of the keys for the standard JWK properties, but other keys can be added via
     * extension properties and functions.
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
     * A builder component for creating a [Jwk] instance. This component should not be created directly, but instead
     * can be used to create a [Jwk] instance via the [Jwk] constructor function.
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
        internal fun build(): Jwk =
            JwkImpl(
                keyType = this.keyType,
                jwkValues = this,
                json = this.json
            )
    }

    public companion object
}

/**
 * Creates a [Jwk] instance using the provided [builder] to set the JWK values.
 */
@ExperimentalJwtApi
public operator fun Jwk.Companion.invoke(
    json: Json,
    keyType: KeyType,
    builder: Jwk.Builder.() -> Unit
): Jwk {
    val jwkBuilder = Jwk.Builder(
        initialKeyType = keyType,
        json = json
    )

    builder.invoke(jwkBuilder)

    return jwkBuilder.build()
}

/**
 * A convenience function for creating a [Jwk] instance with the provided values.
 */
@ExperimentalJwtApi
public operator fun Jwk.Companion.invoke(
    json: Json,
    keyType: KeyType,
    use: KeyUse? = null,
    keyOperations: List<KeyOperation>? = null,
    algorithm: String? = null,
    keyId: String? = null,
    x5u: String? = null,
    x5c: String? = null,
    x5t: String? = null,
    x5tS256: String? = null
): Jwk = Jwk(
    keyType = keyType,
    json = json
) {
    this.keyType = keyType
    this.use = use
    this.keyOperations = keyOperations
    this.algorithm = algorithm
    this.keyId = keyId
    this.x5u = x5u
    this.x5c = x5c
    this.x5t = x5t
    this.x5tS256 = x5tS256
}

@ExperimentalJwtApi
internal class JwkImpl internal constructor(
    override val keyType: KeyType,
    private val jwkValues: Map<String, JsonElement>,
    private val json: Json
) : Jwk {

    override val use: KeyUse?
        get() = jwkValues[Jwk.PropertyKey.USE]?.let {
            json.decodeFromJsonElement(it)
        }

    override val keyOperations: List<KeyOperation>?
        get() = jwkValues[Jwk.PropertyKey.KEY_OPERATIONS]?.let {
            json.decodeFromJsonElement(it)
        }

    override val algorithm: String?
        get() = jwkValues[Jwk.PropertyKey.ALGORITHM]?.let {
            json.decodeFromJsonElement(it)
        }

    override val keyId: String?
        get() = jwkValues[Jwk.PropertyKey.KEY_ID]?.let {
            json.decodeFromJsonElement(it)
        }

    override val x5u: String?
        get() = jwkValues[Jwk.PropertyKey.X5U]?.let {
            json.decodeFromJsonElement(it)
        }

    override val x5c: String?
        get() = jwkValues[Jwk.PropertyKey.X5C]?.let {
            json.decodeFromJsonElement(it)
        }

    override val x5t: String?
        get() = jwkValues[Jwk.PropertyKey.X5T]?.let {
            json.decodeFromJsonElement(it)
        }

    override val x5tS256: String?
        get() = jwkValues[Jwk.PropertyKey.X5T_S256]?.let {
            json.decodeFromJsonElement(it)
        }

    override val entries: Set<Map.Entry<String, JsonElement>>
        get() = jwkValues.entries

    override val keys: Set<String>
        get() = jwkValues.keys

    override val size: Int
        get() = jwkValues.size

    override val values: Collection<JsonElement>
        get() = jwkValues.values

    override fun containsKey(key: String): Boolean =
        jwkValues.containsKey(key)

    override fun containsValue(value: JsonElement): Boolean =
        jwkValues.containsValue(value)

    override fun get(key: String): JsonElement? =
        jwkValues[key]

    override fun isEmpty(): Boolean =
        jwkValues.isEmpty()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JwkImpl) return false

        if (keyType != other.keyType) return false
        if (jwkValues != other.jwkValues) return false

        return json == other.json
    }

    override fun hashCode(): Int {
        var result = keyType.hashCode()
        result = 31 * result + jwkValues.hashCode()
        result = 31 * result + json.hashCode()
        return result
    }

    override fun toString(): String =
        "JwkImpl(keyType='$keyType', jsonObject=$jwkValues, json=$json)"
}

/**
 * The [KSerializer] implementation for the [Jwk] class.
 */
@ExperimentalJwtApi
internal object JwkSerializer : KSerializer<Jwk> {

    private val delegateSerializer = JsonObject.serializer()

    override val descriptor: SerialDescriptor
        get() = delegateSerializer.descriptor

    override fun deserialize(decoder: Decoder): Jwk {
        val jsonDecoder = (decoder as? JsonDecoder)
            ?: error("Deserializing the JWK class only works with JsonDecoder because a JWK is a JSON object by specification.")

        val values = jsonDecoder.decodeSerializableValue(deserializer = delegateSerializer)

        val keyType = values[Jwk.PropertyKey.KEY_TYPE]?.jsonPrimitive?.contentOrNull?.let {
            KeyType(value = it)
        } ?: error("Jwk must have a Key Type property.")

        return JwkImpl(
            json = jsonDecoder.json,
            keyType = keyType,
            jwkValues = values
        )
    }

    override fun serialize(encoder: Encoder, value: Jwk) {
        delegateSerializer.serialize(
            encoder = encoder,
            value = value.toJsonObject()
        )
    }
}
