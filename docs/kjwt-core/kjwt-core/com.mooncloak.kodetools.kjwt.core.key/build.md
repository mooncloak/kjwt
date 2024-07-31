//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](index.md)/[build](build.md)

# build

[common]\
fun [Jwk.Companion](-jwk/-companion/index.md).[build](build.md)(keyType: [KeyType](-key-type/index.md), json: Json = Json.Default, block: [Jwk.Builder](-jwk/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Jwk](-jwk/index.md)

Creates a [Jwk](-jwk/index.md) from the provided builder [block](build.md) and [json](build.md) instance.

[common]\
fun [JwkSet.Companion](-jwk-set/-companion/index.md).[build](build.md)(keys: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Jwk](-jwk/index.md)&gt; = emptyList(), json: Json = Json.Default, block: [JwkSet.Builder](-jwk-set/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [JwkSet](-jwk-set/index.md)

Creates a [JwkSet](-jwk-set/index.md) from the provided builder [block](build.md) and [json](build.md) instance.
