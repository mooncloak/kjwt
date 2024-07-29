package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.BaseJwtObjectSerializer
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.JwtObject
import com.mooncloak.kodetools.kjwt.core.Thumbprint
import com.mooncloak.kodetools.kjwt.core.crypto.HashFunction
import com.mooncloak.kodetools.kjwt.core.crypto.Sha256
import com.mooncloak.kodetools.kjwt.core.property
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

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
    override val json: Json = Json.Default,
    override val properties: Map<String, JsonElement>,
    keyType: KeyType
) : JwtObject() {

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
    public val keyType: KeyType by property(key = PropertyKey.KEY_TYPE, defaultValue = keyType)

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
     * The "crv" (curve) parameter identifies the cryptographic curve used with the key.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.2.1.1)
     */
    public val curve: String? by property(key = PropertyKey.CURVE)

    /**
     * The "x" (x coordinate) parameter contains the x coordinate for the Elliptic Curve point. It
     * is represented as the base64url encoding of the octet string representation of the
     * coordinate, as defined in Section 2.3.5 of SEC1 [SEC1]. The length of this octet string MUST
     * be the full size of a coordinate for the curve specified in the "crv" parameter. For
     * example, if the value of "crv" is "P-521", the octet string must be 66 octets long.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.2.1.2)
     */
    public val x: String? by property(key = PropertyKey.X)

    /**
     * The "y" (y coordinate) parameter contains the y coordinate for the Elliptic Curve point. It
     * is represented as the base64url encoding of the octet string representation of the
     * coordinate, as defined in Section 2.3.5 of SEC1 [SEC1]. The length of this octet string MUST
     * be the full size of a coordinate for the curve specified in the "crv" parameter. For
     * example, if the value of "crv" is "P-521", the octet string must be 66 octets long.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.2.1.3)
     */
    public val y: String? by property(key = PropertyKey.Y)

    /**
     * For elliptic curve private keys, the "d" (ECC private key) parameter contains the Elliptic
     * Curve private key value. It is represented as the base64url encoding of the octet string
     * representation of the private key value, as defined in Section 2.3.7 of SEC1 [SEC1]. The
     * length of this octet string MUST be ceiling(log-base-2(n)/8) octets (where n is the order of
     * the curve).
     *
     * For rsa private keys, the "d" (private exponent) parameter contains the private exponent
     * value for the RSA private key. It is represented as a Base64urlUInt-encoded value.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.2.2.1)
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.1)
     */
    public val d: String? by property(key = PropertyKey.D)

    /**
     * The "n" (modulus) parameter contains the modulus value for the RSA public key. It is
     * represented as a Base64urlUInt-encoded value.
     *
     * Note that implementers have found that some cryptographic libraries prefix an extra
     * zero-valued octet to the modulus representations they return, for instance, returning 257
     * octets for a 2048-bit key, rather than 256. Implementations using such libraries will need
     * to take care to omit the extra octet from the base64url-encoded representation.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.1.1)
     */
    public val n: String? by property(key = PropertyKey.N)

    /**
     * The "e" (exponent) parameter contains the exponent value for the RSA public key. It is
     * represented as a Base64urlUInt-encoded value.
     *
     * For instance, when representing the value 65537, the octet sequence to be base64url-encoded
     * MUST consist of the three octets [1, 0, 1]; the resulting representation for this value is
     * "AQAB".
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.1.2)
     */
    public val e: String? by property(key = PropertyKey.E)

    /**
     * The "p" (first prime factor) parameter contains the first prime factor. It is represented as
     * a Base64urlUInt-encoded value.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.2)
     */
    public val p: String? by property(key = PropertyKey.P)

    /**
     * The "q" (second prime factor) parameter contains the second prime factor. It is represented
     * as a Base64urlUInt-encoded value.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.3)
     */
    public val q: String? by property(key = PropertyKey.Q)

    /**
     * The "dp" (first factor CRT exponent) parameter contains the Chinese Remainder Theorem (CRT)
     * exponent of the first factor. It is represented as a Base64urlUInt-encoded value.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.4)
     */
    public val dp: String? by property(key = PropertyKey.DP)

    /**
     * The "dq" (second factor CRT exponent) parameter contains the CRT exponent of the second
     * factor. It is represented as a Base64urlUInt-encoded value.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.5)
     */
    public val dq: String? by property(key = PropertyKey.DQ)

    /**
     * The "qi" (first CRT coefficient) parameter contains the CRT coefficient of the second
     * factor. It is represented as a Base64urlUInt-encoded value.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.6)
     */
    public val qi: String? by property(key = PropertyKey.QI)

    /**
     * The "oth" (other primes info) parameter contains an array of information about any third and
     * subsequent primes, should they exist. When only two primes have been used (the normal case),
     * this parameter MUST be omitted. When three or more primes have been used, the number of
     * array elements MUST be the number of primes used minus two. For more information on this
     * case, see the description of the OtherPrimeInfo parameters in Appendix A.1.2 of RFC 3447
     * [RFC3447], upon which the following parameters are modeled. If the consumer of a JWK does
     * not support private keys with more than two primes and it encounters a private key that
     * includes the "oth" parameter, then it MUST NOT use the key.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.7)
     */
    public val oth: List<PrimeInfo>? by property(key = PropertyKey.OTH)

    /**
     * The "k" (key value) parameter contains the value of the symmetric (or other single-valued)
     * key. It is represented as the base64url encoding of the octet sequence containing the key
     * value.
     *
     * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.4.1)
     */
    public val k: String? by property(key = PropertyKey.K)

    override fun toString(): String =
        "Jwk(json=$json, properties=$properties)"

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

        /**
         * The key for the [Jwk.x] property.
         */
        public const val X: String = "x"

        /**
         * The key for the [Jwk.y] property.
         */
        public const val Y: String = "y"

        /**
         * The key for the [Jwk.curve] property.
         */
        public const val CURVE: String = "crv"

        /**
         * The key for the [Jwk.d] property.
         */
        public const val D: String = "d"

        /**
         * The key for the [Jwk.n] property.
         */
        public const val N: String = "n"

        /**
         * The key for the [Jwk.e] property.
         */
        public const val E: String = "e"

        /**
         * The key for the [Jwk.p] property.
         */
        public const val P: String = "p"

        /**
         * The key for the [Jwk.q] property.
         */
        public const val Q: String = "q"

        /**
         * The key for the [Jwk.dp] property.
         */
        public const val DP: String = "dp"

        /**
         * The key for the [Jwk.dq] property.
         */
        public const val DQ: String = "dq"

        /**
         * The key for the [Jwk.qi] property.
         */
        public const val QI: String = "qi"

        /**
         * The key for the [Jwk.oth] property.
         */
        public const val OTH: String = "oth"

        /**
         * The key for the [Jwk.k] property.
         */
        public const val K: String = "k"
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
        @Suppress("MemberVisibilityCanBePrivate")
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
         * Gets/sets the [Jwk.curve] value.
         */
        public var curve: String? by property(key = PropertyKey.CURVE)

        /**
         * Gets/sets the [Jwk.x] value.
         */
        public var x: String? by property(key = PropertyKey.X)

        /**
         * Gets/sets the [Jwk.y] value.
         */
        public var y: String? by property(key = PropertyKey.Y)

        /**
         * Gets/sets the [Jwk.d] value.
         */
        public var d: String? by property(key = PropertyKey.D)

        /**
         * Gets/sets the [Jwk.n] value.
         */
        public var n: String? by property(key = PropertyKey.N)

        /**
         * Gets/sets the [Jwk.e] value.
         */
        public var e: String? by property(key = PropertyKey.E)

        /**
         * Gets/sets the [Jwk.p] value.
         */
        public var p: String? by property(key = PropertyKey.P)

        /**
         * Gets/sets the [Jwk.q] value.
         */
        public var q: String? by property(key = PropertyKey.Q)

        /**
         * Gets/sets the [Jwk.dp] value.
         */
        public var dp: String? by property(key = PropertyKey.DP)

        /**
         * Gets/sets the [Jwk.dq] value.
         */
        public var dq: String? by property(key = PropertyKey.DQ)

        /**
         * Gets/sets the [Jwk.qi] value.
         */
        public var qi: String? by property(key = PropertyKey.QI)

        /**
         * Gets/sets the [Jwk.oth] value.
         */
        public var oth: List<PrimeInfo>? by property(key = PropertyKey.OTH)

        /**
         * Gets/sets the [Jwk.k] value.
         */
        public var k: String? by property(key = PropertyKey.K)

        /**
         * Whether the [Jwk.keyId] value should be set to the computed [Jwk.thumbprint] value. If
         * `true`, when the [Jwk.Builder.build] function is invoked, the [Jwk.thumbprint] function
         * will be computed and its result will be set as the [Jwk.keyId] value on the resulting
         * [Jwk]. If `false`, no extra calculation will happen. Defaults to `false`.
         *
         * Note that if this value is set to `true`, the [Jwk.thumbprint] will override any
         * previously set [Jwk.Builder.keyId] value.
         *
         * It is a common practice to set the [Jwk.keyId] as the thumbprint value, but is not
         * mandatory.
         *
         * @see [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638)
         */
        @Suppress("MemberVisibilityCanBePrivate")
        public var computeIdFromThumbprint: Boolean = false

        /**
         * Determines the [HashFunction] that is used to compute the [Jwk.thumbprint] value when
         * the [computeIdFromThumbprint] is set to `true`. Defaults to
         * [HashFunction.Companion.Sha256].
         */
        @Suppress("MemberVisibilityCanBePrivate")
        public var thumbprintHashFunction: HashFunction = HashFunction.Sha256

        /**
         * Converts this [Jwk.Builder] instance into a [Jwk] instance.
         */
        public fun build(): Jwk {
            val jwk = Jwk(
                json = json,
                properties = properties,
                keyType = keyType
            )

            if (computeIdFromThumbprint) {
                val thumbprint = jwk.thumbprint(hashFunction = thumbprintHashFunction)

                return jwk.copy {
                    keyId = thumbprint.value
                }
            }

            return jwk
        }
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
 * Creates a [Jwk] from the provided builder [block] and [json] instance.
 */
@ExperimentalJwtApi
public fun Jwk.Companion.build(
    keyType: KeyType,
    json: Json = Json.Default,
    block: Jwk.Builder.() -> Unit
): Jwk = Jwk.Builder(
    json = json,
    initialKeyType = keyType
).apply(block)
    .build()

/**
 * Creates a JSON Web Key Thumbprint according to the
 * [specification](https://www.rfc-editor.org/rfc/rfc7638).
 *
 * @param [hashFunction] The [HashFunction] used in the thumbprint calculation. Defaults to
 * [HashFunction.Companion.Sha256].
 *
 * @see [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638)
 */
@OptIn(ExperimentalEncodingApi::class)
@ExperimentalJwtApi
public fun Jwk.thumbprint(
    hashFunction: HashFunction = HashFunction.Sha256
): Thumbprint {
    // Create a JSON Object containing only the required properties for a particular algorithm
    // (note that order matters):
    // https://www.rfc-editor.org/rfc/rfc7638#section-3.2

    // Note that the JSON Object implementation seems to use a LinkedHashMap which preserves entry
    // order. However, this could change at any time breaking this implementation.
    // TODO: Guarantee JSON Object entry order.
    val jsonObject = buildJsonObject {
        when (keyType) {
            KeyType.EC -> {
                put(Jwk.PropertyKey.CURVE, curve)
                put(Jwk.PropertyKey.KEY_TYPE, keyType.value)
                put(Jwk.PropertyKey.X, x)
                put(Jwk.PropertyKey.Y, y)
            }

            KeyType.RSA -> {
                put(Jwk.PropertyKey.E, e)
                put(Jwk.PropertyKey.KEY_TYPE, keyType.value)
                put(Jwk.PropertyKey.N, n)
            }

            KeyType.OCT -> {
                put(Jwk.PropertyKey.K, k)
                put(Jwk.PropertyKey.KEY_TYPE, keyType.value)
            }
        }
    }

    // Creates a copy of our current Json instance that asserts that the encoding format will be
    // correct. For example, we must not use pretty printing here, but the default Json instance
    // may be set to use it, so we explicitly make a copy and set that value to `false`. Making a
    // copy is important because it allows us to still use the Serializers Module from the Json
    // instance.
    val json = Json(from = this.json) {
        isLenient = false
        prettyPrint = false
    }

    val jsonString = json.encodeToString(
        serializer = JsonObject.serializer(),
        value = jsonObject
    )
    val utfEncodedBytes = jsonString.encodeToByteArray()
    val hashedBytes = hashFunction.hash(utfEncodedBytes)

    return Thumbprint(value = Base64.UrlSafe.encode(hashedBytes))
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
