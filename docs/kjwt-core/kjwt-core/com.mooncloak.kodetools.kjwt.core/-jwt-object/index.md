//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[JwtObject](index.md)

# JwtObject

abstract class [JwtObject](index.md) : [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt; 

Represents a decoded JsonObject used for JSON Web Token purposes. For instance, this could represent a JsonElement that is stored in a decoded JWT/JWS Token, because, in a JWT, a Header is a JSON Object, and the Body (or Payload) can be a JSON Object as well.

Typically, JWT object constructs are extensible JSON objects. So this interface provides a simple way to extracting extra data from the object.

#### Inheritors

| |
|---|
| [JsonClaims](../-json-claims/index.md) |
| [Header](../-header/index.md) |
| [JwkSet](../-jwk-set/index.md) |
| [Builder](-builder/index.md) |
| [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [common]<br>abstract class [Builder](-builder/index.md) : [JwtObject](index.md), [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt; <br>A component that can be used to create an instance of a [JwtObject](index.md). |
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>@Transient<br>open override val [entries](entries.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[Map.Entry](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;&gt; |
| [keys](keys.md) | [common]<br>@Transient<br>open override val [keys](keys.md): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [size](size.md) | [common]<br>@Transient<br>override val [size](size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [values](values.md) | [common]<br>@Transient<br>open override val [values](values.md): [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;JsonElement&gt; |

## Functions

| Name | Summary |
|---|---|
| [containsKey](contains-key.md) | [common]<br>override fun [containsKey](contains-key.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsValue](contains-value.md) | [common]<br>override fun [containsValue](contains-value.md)(value: JsonElement): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [equals](equals.md) | [common]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](get.md) | [common]<br>operator override fun [get](get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonElement? |
| [getValue](get-value.md) | [common]<br>inline fun &lt;[T](get-value.md)&gt; [getValue](get-value.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deserializer: DeserializationStrategy&lt;[T](get-value.md)&gt; = json.serializersModule.serializer()): [T](get-value.md)? |
| [hashCode](hash-code.md) | [common]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](is-empty.md) | [common]<br>override fun [isEmpty](is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [property](../property.md) | [common]<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](index.md), [V](../property.md)?&gt;<br>inline fun &lt;[V](../property.md)&gt; [JwtObject](index.md).[property](../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, defaultValue: [V](../property.md), deserializer: DeserializationStrategy&lt;[V](../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](index.md), [V](../property.md)&gt;<br>A property delegate to the stored underlying value. |
| [toJsonObject](../to-json-object.md) | [common]<br>fun [JwtObject](index.md).[toJsonObject](../to-json-object.md)(): JsonObject<br>Converts this [JwtObject](index.md) into a JsonObject instance. |
