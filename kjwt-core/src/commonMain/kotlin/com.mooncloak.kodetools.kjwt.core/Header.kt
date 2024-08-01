package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

/**
 * An interface that represents reserved JWT Header parameters.
 *
 * Note that this interface includes both the reserved JWT Header parameters and the JWS Header
 * parameters. Since all the parameters are optional, and this provides type safety and helps to
 * prevent name clashes, it should be fine to include both the JWT and JWS Header parameters in one
 * interface.
 *
 * Note that it is important to make sure that the [SerialName] values for each property are used
 * correctly by implementing classes. Refer to the [PropertyKey] object for the correct
 * [SerialName] usage for each property.
 *
 * @see [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1)
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-5)
 */
@ExperimentalJwtApi
@Serializable(with = HeaderSerializer::class)
public class Header public constructor(
    override val json: Json = Json.Default,
    override val properties: Map<String, JsonElement>
) : JwtObject() {

    /**
     * The "alg" (algorithm) Header Parameter identifies the cryptographic algorithm used to secure
     * the JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.1)
     */
    public val algorithm: String? by property(PropertyKey.ALGORITHM)

    /**
     * The "jku" (JWK Set URL) Header Parameter is a URI
     * [RFC3986](https://datatracker.ietf.org/doc/html/rfc3986) that refers to a resource for a set
     * of JSON-encoded public keys, one of which corresponds to the key used to digitally sign the
     * JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.2)
     */
    public val jwkSetUrl: String? by property(PropertyKey.JWK_SET_URL)

    /**
     * The "jwk" (JSON Web Key) Header Parameter is the public key that corresponds to the key used
     * to digitally sign the JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.3)
     */
    public val jwk: String? by property(PropertyKey.JWK)

    /**
     * The "kid" (key ID) Header Parameter is a hint indicating which key was used to secure the
     * JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.4)
     */
    public val keyId: String? by property(PropertyKey.KEY_ID)

    /**
     * The "x5u" (X.509 URL) Header Parameter is a URI
     * [RFC3986](https://datatracker.ietf.org/doc/html/rfc3986) that refers to a resource for the
     * X.509 public key certificate or certificate chain
     * [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280) corresponding to the key used to
     * digitally sign the JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.5)
     */
    public val x5u: String? by property(PropertyKey.X5U)

    /**
     * The "x5c" (X.509 certificate chain) Header Parameter contains the X.509 public key
     * certificate or certificate chain [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280)
     * corresponding to the key used to digitally sign the JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.6)
     */
    public val x5c: String? by property(PropertyKey.X5C)

    /**
     * The "x5t" (X.509 certificate SHA-1 thumbprint) Header Parameter is a base64url-encoded SHA-1
     * thumbprint (a.k.a. digest) of the DER encoding of the X.509 certificate
     * [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280) corresponding to the key used to
     * digitally sign the JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.7)
     */
    public val x5t: String? by property(PropertyKey.X5T)

    /**
     * The "x5t#S256" (X.509 certificate SHA-256 thumbprint) Header Parameter is a
     * base64url-encoded SHA-256 thumbprint (a.k.a. digest) of the DER encoding of the X.509
     * certificate [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280) corresponding to the
     * key used to digitally sign the JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.8)
     */
    public val x5tS256: String? by property(PropertyKey.X5T_S256)

    /**
     * The "typ" (type) Header Parameter is used by JWS applications to declare the media type
     * [IANA.MediaTypes](https://datatracker.ietf.org/doc/html/rfc7515#ref-IANA.MediaTypes) of this
     * complete JWS.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.9)
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-5.1)
     */
    public val type: String? by property(PropertyKey.TYPE)

    /**
     * The "cty" (content type) Header Parameter is used by JWS applications to declare the media
     * type [IANA.MediaTypes](https://datatracker.ietf.org/doc/html/rfc7515#ref-IANA.MediaTypes) of
     * the secured content (the payload).
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.10)
     * @see [JWT Definition](https://datatracker.ietf.org/doc/html/rfc7519#section-5.2)
     */
    public val contentType: String? by property(PropertyKey.CONTENT_TYPE)

    /**
     * The "crit" (critical) Header Parameter indicates that extensions to this specification
     * and/or [JWA](https://datatracker.ietf.org/doc/html/rfc7515#ref-JWA) are being used that MUST
     * be understood and processed.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7515#section-4.1.11)
     */
    public val critical: String? by property(PropertyKey.CRITICAL)

    /**
     * The "zip" (compression algorithm) Header Parameter indicates the compression algorithm
     * applied to the plaintext before encryption, if any.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7516#section-4.1.3)
     */
    public val compression: String? by property(PropertyKey.COMPRESSION)

    /**
     * The "enc" (encryption algorithm) Header Parameter identifies the content encryption
     * algorithm used to perform authenticated encryption on the plaintext to produce the
     * ciphertext and the Authentication Tag.
     *
     * Value defaults to `null`.
     *
     * @see [JWS Definition](https://datatracker.ietf.org/doc/html/rfc7516#section-4.1.2)
     */
    public val encryption: String? by property(PropertyKey.ENCRYPTION)

    override fun toString(): String =
        "Header(json=$json, properties=$properties)"

    /**
     * Header key values. This consists of the keys for the standard header values, but other keys
     * can be added via extension properties and functions.
     */
    public object PropertyKey {

        /**
         * The key for the [Header.algorithm] property. This constant value is equal to "alg".
         */
        public const val ALGORITHM: String = "alg"

        /**
         * The key for the [Header.jwkSetUrl] property. This constant value is equal to "jku".
         */
        public const val JWK_SET_URL: String = "jku"

        /**
         * The key for the [Header.jwk] property. This constant value is equal to "jwk".
         */
        public const val JWK: String = "jwk"

        /**
         * The key for the [Header.keyId] property. This constant value is equal to "kid".
         */
        public const val KEY_ID: String = "kid"

        /**
         * The key for the [Header.x5u] property. This constant value is equal to "x5u".
         */
        public const val X5U: String = "x5u"

        /**
         * The key for the [Header.x5c] property. This constant value is equal to "x5c".
         */
        public const val X5C: String = "x5c"

        /**
         * The key for the [Header.x5t] property. This constant value is equal to "x5t".
         */
        public const val X5T: String = "x5t"

        /**
         * The key for the [Header.x5tS256] property. This constant value is equal to "x5t#S256".
         */
        public const val X5T_S256: String = "x5t#S256"

        /**
         * The key for the [Header.type] property. This constant value is equal to "typ".
         */
        public const val TYPE: String = "typ"

        /**
         * The key for the [Header.contentType] property. This constant value is equal to "cty".
         */
        public const val CONTENT_TYPE: String = "cty"

        /**
         * The key for the [Header.critical] property. This constant value is equal to "crit".
         */
        public const val CRITICAL: String = "crit"

        /**
         * The key for the [Header.compression] property. This constant value is equal to "zip".
         */
        public const val COMPRESSION: String = "zip"

        /**
         * The key for the [Header.encryption] property. This constant value is equal to "enc".
         */
        public const val ENCRYPTION: String = "enc"
    }

    /**
     * A builder component for creating a [Header] instance. This component should not be created
     * directly, but instead can be used to create a [Header] instance via the [Header] constructor
     * function.
     */
    public class Builder internal constructor(
        override val json: Json,
        initialValues: Map<String, JsonElement> = emptyMap()
    ) : JwtObject.Builder(initialValues) {

        /**
         * Gets/sets the [Header.algorithm] value.
         */
        public var algorithm: String? by property(key = PropertyKey.ALGORITHM)

        /**
         * Gets/sets the [Header.jwkSetUrl] value.
         */
        public var jwkSetUrl: String? by property(key = PropertyKey.JWK_SET_URL)

        /**
         * Gets/sets the [Header.jwk] value.
         */
        public var jwk: String? by property(key = PropertyKey.JWK)

        /**
         * Gets/sets the [Header.keyId] value.
         */
        public var keyId: String? by property(key = PropertyKey.KEY_ID)

        /**
         * Gets/sets the [Header.x5u] value.
         */
        public var x5u: String? by property(key = PropertyKey.X5U)

        /**
         * Gets/sets the [Header.x5c] value.
         */
        public var x5c: String? by property(key = PropertyKey.X5C)

        /**
         * Gets/sets the [Header.x5t] value.
         */
        public var x5t: String? by property(key = PropertyKey.X5T)

        /**
         * Gets/sets the [Header.x5tS256] value.
         */
        public var x5tS256: String? by property(key = PropertyKey.X5T_S256)

        /**
         * Gets/sets the [Header.type] value.
         */
        public var type: String? by property(key = PropertyKey.TYPE)

        /**
         * Gets/sets the [Header.contentType] value.
         */
        public var contentType: String? by property(key = PropertyKey.CONTENT_TYPE)

        /**
         * Gets/sets the [Header.critical] value.
         */
        public var critical: String? by property(key = PropertyKey.CRITICAL)

        /**
         * Gets/sets the [Header.compression] value.
         */
        public var compression: String? by property(key = PropertyKey.COMPRESSION)

        /**
         * Gets/sets the [Header.encryption] value.
         */
        public var encryption: String? by property(key = PropertyKey.ENCRYPTION)

        /**
         * Creates a [Header] instance from this [Header.Builder].
         */
        public fun build(): Header = Header(
            json = json,
            properties = properties
        )
    }

    public companion object
}

/**
 * Retrieves the [SignatureAlgorithm] algorithm used to sign the [Jws] token associated with this
 * [Header], or `null` if a matching [SignatureAlgorithm] could not be found or the
 * [Header.algorithm] value was `null`.
 */
@ExperimentalJwtApi
public val Header.signatureAlgorithm: SignatureAlgorithm?
    get() = this.algorithm?.let { SignatureAlgorithm.getBySerialName(it) }

/**
 * Gets or sets the [SignatureAlgorithm] algorithm used to sign the [Jws] token associated with
 * this [Header], or `null` if a matching [SignatureAlgorithm] could not be found or the
 * [Header.algorithm] value was `null`.
 */
@ExperimentalJwtApi
public var Header.Builder.signatureAlgorithm: SignatureAlgorithm?
    get() = this.algorithm?.let { SignatureAlgorithm.getBySerialName(it) }
    set(value) {
        this.algorithm = value?.serialName
    }

/**
 * Converts this [Header] instance into a [Header.Builder].
 */
@ExperimentalJwtApi
public fun Header.toBuilder(): Header.Builder =
    Header.Builder(
        json = this.json,
        initialValues = this.properties
    )

/**
 * Creates a new [Header] instance starting with the same values from this [Header] instance which
 * can be overridden from the provided builder [block].
 */
@ExperimentalJwtApi
public fun Header.copy(block: Header.Builder.() -> Unit = {}): Header {
    val builder = this.toBuilder().apply(block)

    return builder.build()
}

/**
 * Creates a [Header] from the provided builder [block] and [json] instance.
 */
@ExperimentalJwtApi
public fun Header.Companion.build(
    json: Json = Json.Default,
    block: Header.Builder.() -> Unit
): Header =
    Header.Builder(json = json).apply(block).build()

/**
 * Creates a [Header] from the provided builder [block] and [json] instance. This is a convenience
 * function for invoking [build].
 */
@ExperimentalJwtApi
public operator fun Header.Companion.invoke(
    json: Json = Json.Default,
    block: Header.Builder.() -> Unit = {}
): Header = build(
    json = json,
    block = block
)

@ExperimentalJwtApi
internal class HeaderSerializer internal constructor() : BaseJwtObjectSerializer<Header>() {

    override fun toType(json: Json, jsonObject: JsonObject): Header =
        Header(
            json = json,
            properties = jsonObject
        )
}
