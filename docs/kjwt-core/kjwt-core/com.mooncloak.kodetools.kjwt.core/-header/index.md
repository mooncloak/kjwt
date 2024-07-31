//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[Header](index.md)

# Header

@Serializable(with = [HeaderSerializer::class](../../../../kjwt-core/com.mooncloak.kodetools.kjwt.core/-header-serializer/index.md))

class [Header](index.md)(json: Json = Json.Default, properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;) : [JwtObject](../-jwt-object/index.md)

An interface that represents reserved JWT Header parameters.

Note that this interface includes both the reserved JWT Header parameters and the JWS Header parameters. Since all the parameters are optional, and this provides type safety and helps to prevent name clashes, it should be fine to include both the JWT and JWS Header parameters in one interface.

Note that it is important to make sure that the SerialName values for each property are used correctly by implementing classes. Refer to the [PropertyKey](-property-key/index.md) object for the correct SerialName usage for each property.

#### See also

| | |
|---|---|
|  | [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-5) |

## Constructors

| | |
|---|---|
| [Header](-header.md) | [common]<br>constructor(json: Json = Json.Default, properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [common]<br>class [Builder](-builder/index.md) : [JwtObject.Builder](../-jwt-object/-builder/index.md)<br>A builder component for creating a [Header](index.md) instance. This component should not be created directly, but instead can be used to create a [Header](index.md) instance via the [Header](index.md) constructor function. |
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |
| [PropertyKey](-property-key/index.md) | [common]<br>object [PropertyKey](-property-key/index.md)<br>Header key values. This consists of the keys for the standard header values, but other keys can be added via extension properties and functions. |

## Properties

| Name | Summary |
|---|---|
| [algorithm](algorithm.md) | [common]<br>val [algorithm](algorithm.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;alg&quot; (algorithm) Header Parameter identifies the cryptographic algorithm used to secure the JWS. |
| [compression](compression.md) | [common]<br>val [compression](compression.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;zip&quot; (compression algorithm) Header Parameter indicates the compression algorithm applied to the plaintext before encryption, if any. |
| [contentType](content-type.md) | [common]<br>val [contentType](content-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;cty&quot; (content type) Header Parameter is used by JWS applications to declare the media type [IANA.MediaTypes](https://datatracker.ietf.org/doc/html/rfc7515#ref-IANA.MediaTypes) of the secured content (the payload). |
| [critical](critical.md) | [common]<br>val [critical](critical.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;crit&quot; (critical) Header Parameter indicates that extensions to this specification and/or [JWA](https://datatracker.ietf.org/doc/html/rfc7515#ref-JWA) are being used that MUST be understood and processed. |
| [encryption](encryption.md) | [common]<br>val [encryption](encryption.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;enc&quot; (encryption algorithm) Header Parameter identifies the content encryption algorithm used to perform authenticated encryption on the plaintext to produce the ciphertext and the Authentication Tag. |
| [entries](../-jwt-object/entries.md) | [common]<br>@Transient<br>open override val [entries](../-jwt-object/entries.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[Map.Entry](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;&gt; |
| [jwk](jwk.md) | [common]<br>val [jwk](jwk.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;jwk&quot; (JSON Web Key) Header Parameter is the public key that corresponds to the key used to digitally sign the JWS. |
| [jwkSetUrl](jwk-set-url.md) | [common]<br>val [jwkSetUrl](jwk-set-url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;jku&quot; (JWK Set URL) Header Parameter is a URI [RFC3986](https://datatracker.ietf.org/doc/html/rfc3986) that refers to a resource for a set of JSON-encoded public keys, one of which corresponds to the key used to digitally sign the JWS. |
| [keyId](key-id.md) | [common]<br>val [keyId](key-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;kid&quot; (key ID) Header Parameter is a hint indicating which key was used to secure the JWS. |
| [keys](../-jwt-object/keys.md) | [common]<br>@Transient<br>open override val [keys](../-jwt-object/keys.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [signatureAlgorithm](../signature-algorithm.md) | [common]<br>val [Header](index.md).[signatureAlgorithm](../signature-algorithm.md): [SignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md)?<br>Retrieves the [SignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md) algorithm used to sign the [Jws](../-jws/index.md) token associated with this [Header](index.md), or `null` if a matching [SignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md) could not be found or the [Header.algorithm](algorithm.md) value was `null`. |
| [size](../-jwt-object/size.md) | [common]<br>@Transient<br>override val [size](../-jwt-object/size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [type](type.md) | [common]<br>val [type](type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;typ&quot; (type) Header Parameter is used by JWS applications to declare the media type [IANA.MediaTypes](https://datatracker.ietf.org/doc/html/rfc7515#ref-IANA.MediaTypes) of this complete JWS. |
| [values](../-jwt-object/values.md) | [common]<br>@Transient<br>open override val [values](../-jwt-object/values.md): [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;JsonElement&gt; |
| [x5c](x5c.md) | [common]<br>val [x5c](x5c.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;x5c&quot; (X.509 certificate chain) Header Parameter contains the X.509 public key certificate or certificate chain [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280) corresponding to the key used to digitally sign the JWS. |
| [x5t](x5t.md) | [common]<br>val [x5t](x5t.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;x5t&quot; (X.509 certificate SHA-1 thumbprint) Header Parameter is a base64url-encoded SHA-1 thumbprint (a.k.a. digest) of the DER encoding of the X.509 certificate [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280) corresponding to the key used to digitally sign the JWS. |
| [x5tS256](x5t-s256.md) | [common]<br>val [x5tS256](x5t-s256.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;x5t#S256&quot; (X.509 certificate SHA-256 thumbprint) Header Parameter is a base64url-encoded SHA-256 thumbprint (a.k.a. digest) of the DER encoding of the X.509 certificate [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280) corresponding to the key used to digitally sign the JWS. |
| [x5u](x5u.md) | [common]<br>val [x5u](x5u.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;x5u&quot; (X.509 URL) Header Parameter is a URI [RFC3986](https://datatracker.ietf.org/doc/html/rfc3986) that refers to a resource for the X.509 public key certificate or certificate chain [RFC5280](https://datatracker.ietf.org/doc/html/rfc5280) corresponding to the key used to digitally sign the JWS. |

## Functions

| Name | Summary |
|---|---|
| [containsKey](../-jwt-object/contains-key.md) | [common]<br>override fun [containsKey](../-jwt-object/contains-key.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsValue](../-jwt-object/contains-value.md) | [common]<br>override fun [containsValue](../-jwt-object/contains-value.md)(value: JsonElement): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [copy](../copy.md) | [common]<br>fun [Header](index.md).[copy](../copy.md)(block: [Header.Builder](-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [Header](index.md)<br>Creates a new [Header](index.md) instance starting with the same values from this [Header](index.md) instance which can be overridden from the provided builder [block](../copy.md). |
| [equals](../-jwt-object/equals.md) | [common]<br>open operator override fun [equals](../-jwt-object/equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](../-jwt-object/get.md) | [common]<br>operator override fun [get](../-jwt-object/get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonElement? |
| [getValue](../-jwt-object/get-value.md) | [common]<br>inline fun &lt;[T](../-jwt-object/get-value.md)&gt; [getValue](../-jwt-object/get-value.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deserializer: DeserializationStrategy&lt;[T](../-jwt-object/get-value.md)&gt; = json.serializersModule.serializer()): [T](../-jwt-object/get-value.md)? |
| [hashCode](../-jwt-object/hash-code.md) | [common]<br>open override fun [hashCode](../-jwt-object/hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](../-jwt-object/is-empty.md) | [common]<br>override fun [isEmpty](../-jwt-object/is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [property](../property.md) | [common]<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](../-jwt-object/index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../-jwt-object/index.md), [V](../property.md)?&gt;<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](../-jwt-object/index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, defaultValue: [V](../property.md), deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../-jwt-object/index.md), [V](../property.md)&gt;<br>A property delegate to the stored underlying value. |
| [toBuilder](../to-builder.md) | [common]<br>fun [Header](index.md).[toBuilder](../to-builder.md)(): [Header.Builder](-builder/index.md)<br>Converts this [Header](index.md) instance into a [Header.Builder](-builder/index.md). |
| [toJsonObject](../to-json-object.md) | [common]<br>fun [JwtObject](../-jwt-object/index.md).[toJsonObject](../to-json-object.md)(): JsonObject<br>Converts this [JwtObject](../-jwt-object/index.md) into a JsonObject instance. |
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
