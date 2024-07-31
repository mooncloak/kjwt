//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[JwkSet](index.md)

# JwkSet

@Serializable(with = [JwkSetSerializer::class](../../../../kjwt-core/com.mooncloak.kodetools.kjwt.core/-jwk-set-serializer/index.md))

class [JwkSet](index.md)(json: Json = Json.Default, properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;, keys: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md)&gt;) : [JwtObject](../-jwt-object/index.md)

Represents a JWK (JSON Web Key) Set.

#### See also

| | |
|---|---|
|  | [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517) |

## Constructors

| | |
|---|---|
| [JwkSet](-jwk-set.md) | [common]<br>constructor(json: Json = Json.Default, properties: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;, keys: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md)&gt;) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [common]<br>class [Builder](-builder/index.md) : [JwtObject.Builder](../-jwt-object/-builder/index.md)<br>A builder component for creating a [JwkSet](index.md) instance. This component should not be created directly, but instead can be used to create a [JwkSet](index.md) instance via the [JwkSet](index.md) constructor function. |
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |
| [PropertyKey](-property-key/index.md) | [common]<br>object [PropertyKey](-property-key/index.md) |

## Properties

| Name | Summary |
|---|---|
| [entries](../-jwt-object/entries.md) | [common]<br>@Transient<br>open override val [entries](../-jwt-object/entries.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[Map.Entry](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;&gt; |
| [jwkKeys](jwk-keys.md) | [common]<br>val [jwkKeys](jwk-keys.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md)&gt; |
| [keys](../-jwt-object/keys.md) | [common]<br>@Transient<br>open override val [keys](../-jwt-object/keys.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [size](../-jwt-object/size.md) | [common]<br>@Transient<br>override val [size](../-jwt-object/size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [values](../-jwt-object/values.md) | [common]<br>@Transient<br>open override val [values](../-jwt-object/values.md): [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;JsonElement&gt; |

## Functions

| Name | Summary |
|---|---|
| [containsKey](../-jwt-object/contains-key.md) | [common]<br>override fun [containsKey](../-jwt-object/contains-key.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsValue](../-jwt-object/contains-value.md) | [common]<br>override fun [containsValue](../-jwt-object/contains-value.md)(value: JsonElement): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [copy](../copy.md) | [common]<br>fun [JwkSet](index.md).[copy](../copy.md)(block: [JwkSet.Builder](-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [JwkSet](index.md)<br>Creates a new [JwkSet](index.md) instance starting with the same values from this [JwkSet](index.md) instance which can be overridden from the provided builder [block](../copy.md). |
| [equals](../-jwt-object/equals.md) | [common]<br>open operator override fun [equals](../-jwt-object/equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](../-jwt-object/get.md) | [common]<br>operator override fun [get](../-jwt-object/get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonElement? |
| [getValue](../-jwt-object/get-value.md) | [common]<br>inline fun &lt;[T](../-jwt-object/get-value.md)&gt; [getValue](../-jwt-object/get-value.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deserializer: DeserializationStrategy&lt;[T](../-jwt-object/get-value.md)&gt; = json.serializersModule.serializer()): [T](../-jwt-object/get-value.md)? |
| [hashCode](../-jwt-object/hash-code.md) | [common]<br>open override fun [hashCode](../-jwt-object/hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](../-jwt-object/is-empty.md) | [common]<br>override fun [isEmpty](../-jwt-object/is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [property](../property.md) | [common]<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](../-jwt-object/index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../-jwt-object/index.md), [V](../property.md)?&gt;<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](../-jwt-object/index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, defaultValue: [V](../property.md), deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../-jwt-object/index.md), [V](../property.md)&gt;<br>A property delegate to the stored underlying value. |
| [toBuilder](../to-builder.md) | [common]<br>fun [JwkSet](index.md).[toBuilder](../to-builder.md)(): [JwkSet.Builder](-builder/index.md)<br>Converts this [JwkSet](index.md) instance into a [JwkSet.Builder](-builder/index.md). |
| [toJsonObject](../to-json-object.md) | [common]<br>fun [JwtObject](../-jwt-object/index.md).[toJsonObject](../to-json-object.md)(): JsonObject<br>Converts this [JwtObject](../-jwt-object/index.md) into a JsonObject instance. |
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
