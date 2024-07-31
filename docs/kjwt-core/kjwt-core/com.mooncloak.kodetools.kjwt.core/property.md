//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core](index.md)/[property](property.md)

# property

[common]\
inline fun &lt;[V](property.md)&gt; [JwtObject](-jwt-object/index.md).[property](property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, defaultValue: [V](property.md), deserializer: DeserializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](-jwt-object/index.md), [V](property.md)&gt;

inline fun &lt;[V](property.md)&gt; [JwtObject](-jwt-object/index.md).[property](property.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, deserializer: DeserializationStrategy&lt;[V](property.md)&gt; = json.serializersModule.serializer()): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)&lt;[JwtObject](-jwt-object/index.md), [V](property.md)?&gt;

A property delegate to the stored underlying value.
