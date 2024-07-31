//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../../index.md)/[JwtObject](../index.md)/[Builder](index.md)/[property](property.md)

# property

[common]\
inline fun &lt;[V](property.md)&gt; [JwtObject](../index.md).[property](property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, initialValue: [V](property.md), serializer: SerializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer(), deserializer: DeserializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer()): [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)&lt;[JwtObject.Builder](index.md), [V](property.md)&gt;

inline fun &lt;[V](property.md)&gt; [JwtObject](../index.md).[property](property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, serializer: SerializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer(), deserializer: DeserializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer()): [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)&lt;[JwtObject.Builder](index.md), [V](property.md)?&gt;

A property delegate to the stored underlying value.
