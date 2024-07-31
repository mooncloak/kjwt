//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../../index.md)/[JwtObject](../index.md)/[Builder](index.md)

# Builder

abstract class [Builder](index.md) : [JwtObject](../index.md), [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt; 

A component that can be used to create an instance of a [JwtObject](../index.md).

#### Inheritors

| |
|---|
| [Builder](../../-json-claims/-builder/index.md) |
| [Builder](../../-header/-builder/index.md) |
| [Builder](../../../com.mooncloak.kodetools.kjwt.core.key/-jwk/-builder/index.md) |
| [Builder](../../../com.mooncloak.kodetools.kjwt.core.key/-jwk-set/-builder/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>open override val [entries](entries.md): [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;[MutableMap.MutableEntry](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/-mutable-entry/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;&gt; |
| [keys](keys.md) | [common]<br>open override val [keys](keys.md): [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [size](../size.md) | [common]<br>@Transient<br>override val [size](../size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [values](values.md) | [common]<br>open override val [values](values.md): [MutableCollection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-collection/index.html)&lt;JsonElement&gt; |

## Functions

| Name | Summary |
|---|---|
| [clear](clear.md) | [common]<br>open override fun [clear](clear.md)() |
| [containsKey](../contains-key.md) | [common]<br>override fun [containsKey](../contains-key.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsValue](../contains-value.md) | [common]<br>override fun [containsValue](../contains-value.md)(value: JsonElement): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [equals](equals.md) | [common]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [get](../get.md) | [common]<br>operator override fun [get](../get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonElement? |
| [getValue](../get-value.md) | [common]<br>inline fun &lt;[T](../get-value.md)&gt; [getValue](../get-value.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), deserializer: DeserializationStrategy&lt;[T](../get-value.md)&gt; = json.serializersModule.serializer()): [T](../get-value.md)? |
| [hashCode](hash-code.md) | [common]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](../is-empty.md) | [common]<br>override fun [isEmpty](../is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [property](property.md) | [common]<br>inline fun &lt;[V](property.md)&gt; [JwtObject](../index.md).[property](property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, serializer: SerializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer(), deserializer: DeserializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer()): [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)&lt;[JwtObject.Builder](index.md), [V](property.md)?&gt;<br>inline fun &lt;[V](property.md)&gt; [JwtObject](../index.md).[property](property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, initialValue: [V](property.md), serializer: SerializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer(), deserializer: DeserializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer()): [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)&lt;[JwtObject.Builder](index.md), [V](property.md)&gt;<br>A property delegate to the stored underlying value. |
| [property](../../property.md) | [common]<br>inline fun &lt;[V](../../property.md)&gt; [JwtObject](../index.md).[property](../../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, deserializer: DeserializationStrategy&lt;[V](../../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../index.md), [V](../../property.md)?&gt;<br>inline fun &lt;[V](../../property.md)&gt; [JwtObject](../index.md).[property](../../property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, defaultValue: [V](../../property.md), deserializer: DeserializationStrategy&lt;[V](../../property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](../index.md), [V](../../property.md)&gt;<br>A property delegate to the stored underlying value. |
| [put](put.md) | [common]<br>open override fun [put](put.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: JsonElement): JsonElement? |
| [putAll](put-all.md) | [common]<br>open override fun [putAll](put-all.md)(from: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;out [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), JsonElement&gt;) |
| [putValue](put-value.md) | [common]<br>inline fun &lt;[T](put-value.md)&gt; [putValue](put-value.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [T](put-value.md), serializer: KSerializer&lt;[T](put-value.md)&gt; = json.serializersModule.serializer()) |
| [remove](remove.md) | [common]<br>open override fun [remove](remove.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): JsonElement? |
| [toJsonObject](../../to-json-object.md) | [common]<br>fun [JwtObject](../index.md).[toJsonObject](../../to-json-object.md)(): JsonObject<br>Converts this [JwtObject](../index.md) into a JsonObject instance. |
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
