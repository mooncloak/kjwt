//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../../index.md)/[KeyResolver](../index.md)/[Companion](index.md)

# Companion

[common]\
object [Companion](index.md)

## Properties

| Name | Summary |
|---|---|
| [AlwaysNull](../../-always-null.md) | [common]<br>val [KeyResolver.Companion](index.md).[AlwaysNull](../../-always-null.md): [KeyResolver](../index.md)<br>Retrieves a [KeyResolver](../index.md) instance whose [KeyResolver.resolve](../resolve.md) function always returns `null`. This could be useful for testing purposes. |
| [Unsupported](../../-unsupported.md) | [common]<br>val [KeyResolver.Companion](index.md).[Unsupported](../../-unsupported.md): [KeyResolver](../index.md)<br>Retrieves a [KeyResolver](../index.md) instance whose [KeyResolver.resolve](../resolve.md) function always throws an [UnsupportedJwtSignatureAlgorithm](../../../com.mooncloak.kodetools.kjwt.core/-unsupported-jwt-signature-algorithm/index.md). This could be useful for testing purposes. |

## Functions

| Name | Summary |
|---|---|
| [aggregate](../../aggregate.md) | [common]<br>fun [KeyResolver.Companion](index.md).[aggregate](../../aggregate.md)(vararg resolvers: [KeyResolver](../index.md)): [KeyResolver](../index.md)<br>fun [KeyResolver.Companion](index.md).[aggregate](../../aggregate.md)(resolvers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[KeyResolver](../index.md)&gt;): [KeyResolver](../index.md)<br>Retrieves a [KeyResolver](../index.md) instance that uses the provided [resolvers](../../aggregate.md) to resolve a [Jwk](../../-jwk/index.md). |
| [of](../../of.md) | [common]<br>fun [KeyResolver.Companion](index.md).[of](../../of.md)(keys: [JwkSet](../../../com.mooncloak.kodetools.kjwt.core/-jwk-set/index.md)): [KeyResolver](../index.md)<br>Retrieves a [KeyResolver](../index.md) instance that chooses from [Jwk](../../-jwk/index.md)s defined in the provided [JwkSet](../../../com.mooncloak.kodetools.kjwt.core/-jwk-set/index.md) by comparing the [Jwk.keyId](../../-jwk/key-id.md) value with the [Header.keyId](../../../com.mooncloak.kodetools.kjwt.core/-header/key-id.md) value.<br>[common]<br>fun [KeyResolver.Companion](index.md).[of](../../of.md)(key: [Jwk](../../-jwk/index.md)?): [KeyResolver](../index.md)<br>Retrieves a [KeyResolver](../index.md) instance that always resolves the provided [key](../../of.md) value. This is a convenience function. |
