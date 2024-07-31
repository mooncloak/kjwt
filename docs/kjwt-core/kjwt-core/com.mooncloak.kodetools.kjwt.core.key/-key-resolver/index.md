//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[KeyResolver](index.md)

# KeyResolver

[common]\
fun interface [KeyResolver](index.md)

A component that resolves the [Jwk](../-jwk/index.md) key used when signing or verifying a [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md). This could be used to dynamically resolve the signing key for verification or signing purposes.

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [resolve](resolve.md) | [common]<br>abstract suspend fun [resolve](resolve.md)(header: [Header](../../com.mooncloak.kodetools.kjwt.core/-header/index.md), operation: [KeyOperation](../-key-operation/index.md)): [Jwk](../-jwk/index.md)?<br>Returns the signing key that should be used to validate a digital signature for the Claims JWS with the specified header and claims. |
