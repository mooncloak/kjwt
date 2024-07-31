//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[JsonClaims](index.md)

# JsonClaims

@Serializable(with = [JsonClaimsSerializer::class](../../../../kjwt-core/com.mooncloak.kodetools.kjwt.core/-json-claims-serializer/index.md))

class [JsonClaims](index.md)(json: Json = Json.Default, properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;) : [JwtObject](../-jwt-object/index.md), [Claims](../-claims/index.md)

An interface that represents a JWT body's payload claims.

#### See also

| | |
|---|---|
|  | [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-4.1) |

## Constructors

| | |
|---|---|
| [JsonClaims](-json-claims.md) | [common]<br>constructor(json: Json = Json.Default, properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [common]<br>class [Builder](-builder/index.md) : [JwtObject.Builder](../-jwt-object/-builder/index.md)<br>A builder component for creating a [JsonClaims](index.md) instance. This component should not be created directly, but instead can be used to create a [JsonClaims](index.md) instance via the [JsonClaims](index.md) constructor function. |
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |
| [PropertyKey](-property-key/index.md) | [common]<br>object [PropertyKey](-property-key/index.md)<br>Claim key values. This consists of the keys for the standard claim values, but other keys can be added via extension properties and functions. |

## Properties

| Name | Summary |
|---|---|
| [audience](audience.md) | [common]<br>val [audience](audience.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;?<br>The &quot;aud&quot; (audience) claim identifies the recipients that the JWT is intended for. |
| [entries](../-jwt-object/entries.md) | [common]<br>@Transient<br>open override val [entries](../-jwt-object/entries.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[Map.Entry](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;&gt; |
| [expiration](expiration.md) | [common]<br>val [expiration](expiration.md): Instant?<br>The &quot;exp&quot; (expiration time) claim identifies the expiration time on or after which the JWT MUST NOT be accepted for processing. This value should be a &quot;Numeric Date&quot; which is a JSON number value representing the **seconds** since the Epoch. |
| [id](id.md) | [common]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;jti&quot; (JWT ID) claim provides a unique identifier for the JWT. |
| [issuedAt](issued-at.md) | [common]<br>val [issuedAt](issued-at.md): Instant?<br>The &quot;iat&quot; (issued at) claim identifies the time at which the JWT was issued. This value should be a &quot;Numeric Date&quot; which is a JSON number value representing the **seconds** since the Epoch. |
| [issuer](issuer.md) | [common]<br>val [issuer](issuer.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;iss&quot; (issuer) claim identifies the principal that issued the JWT. |
| [keys](../-jwt-object/keys.md) | [common]<br>@Transient<br>open override val [keys](../-jwt-object/keys.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [notBefore](not-before.md) | [common]<br>val [notBefore](not-before.md): Instant?<br>The &quot;nbf&quot; (not before) claim identifies the time before which the JWT MUST NOT be accepted for processing. This value should be a &quot;Numeric Date&quot; which is a JSON number value representing the **seconds** since the Epoch. |
| [size](../-jwt-object/size.md) | [common]<br>@Transient<br>override val [size](../-jwt-object/size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [subject](subject.md) | [common]<br>val [subject](subject.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The &quot;sub&quot; (subject) claim identifies the principal that is the subject of the JWT. |
| [values](../-jwt-object/values.md) | [common]<br>@Transient<br>open override val [values](../-jwt-object/values.md): [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;JsonElement&gt; |

## Functions

| Name | Summary |
|---|---|
| [containsKey](../-jwt-object/contains-key.md) | [common]<br>override fun [containsKey](../-jwt-object/contains-key.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsValue](../-jwt-object/contains-value.md) | [common]<br>override fun [containsValue](../-jwt-object/contains-value.md)(value: JsonElement): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [copy](../copy.md) | [common]<br>fun [JsonClaims](index.md).[copy](../copy.md)(block: [JsonClaims.Builder](-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [JsonClaims](index.md)<br>Creates a new [JsonClaims](index.md) instance starting with the same values from this [JsonClaims](index.md) instance which can be overridden from the provided builder [block](../copy.md). |
| [get](../-jwt-object/get.md) | [common]<br>operator override fun [get](../-jwt-object/get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonElement? |
| [getValue](../-jwt-object/get-value.md) | [common]<br>inline fun &lt;[T](../-jwt-object/get-value.md)&gt; [getValue](../-jwt-object/get-value.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deserializer: DeserializationStrategy&lt;[T](../-jwt-object/get-value.md)&gt; = json.serializersModule.serializer()): [T](../-jwt-object/get-value.md)? |
| [isEmpty](../-jwt-object/is-empty.md) | [common]<br>override fun [isEmpty](../-jwt-object/is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [property](../property.md) | [common]<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](../-jwt-object/index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../-jwt-object/index.md), [V](../property.md)?&gt;<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](../-jwt-object/index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, defaultValue: [V](../property.md), deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../-jwt-object/index.md), [V](../property.md)&gt;<br>A property delegate to the stored underlying value. |
| [toBuilder](../to-builder.md) | [common]<br>fun [JsonClaims](index.md).[toBuilder](../to-builder.md)(): [JsonClaims.Builder](-builder/index.md)<br>Converts this [JsonClaims](index.md) instance into a [JsonClaims.Builder](-builder/index.md). |
| [toJsonObject](../to-json-object.md) | [common]<br>fun [JwtObject](../-jwt-object/index.md).[toJsonObject](../to-json-object.md)(): JsonObject<br>Converts this [JwtObject](../-jwt-object/index.md) into a JsonObject instance. |
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
