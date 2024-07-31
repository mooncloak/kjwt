//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](index.md)/[aggregate](aggregate.md)

# aggregate

[common]\
fun [KeyResolver.Companion](-key-resolver/-companion/index.md).[aggregate](aggregate.md)(vararg resolvers: [KeyResolver](-key-resolver/index.md)): [KeyResolver](-key-resolver/index.md)

fun [KeyResolver.Companion](-key-resolver/-companion/index.md).[aggregate](aggregate.md)(resolvers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[KeyResolver](-key-resolver/index.md)&gt;): [KeyResolver](-key-resolver/index.md)

Retrieves a [KeyResolver](-key-resolver/index.md) instance that uses the provided [resolvers](aggregate.md) to resolve a [Jwk](-jwk/index.md).

#### Return

A [KeyResolver](-key-resolver/index.md) that delegates to the provided [KeyResolver](-key-resolver/index.md)s in-order.

#### Parameters

common

| | |
|---|---|
| resolvers | The [KeyResolver](-key-resolver/index.md)s used to resolve a [Jwk](-jwk/index.md). When the returned [KeyResolver](-key-resolver/index.md)'s [KeyResolver.resolve](-key-resolver/resolve.md) function is invoked, the provided [resolvers](aggregate.md) are invoked in-order until one of them returns a non-null [Jwk](-jwk/index.md), and if no resolver returns a non-null [Jwk](-jwk/index.md), then `null` is returned. |
