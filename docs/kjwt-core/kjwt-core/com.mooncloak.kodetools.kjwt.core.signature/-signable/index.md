//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[Signable](index.md)

# Signable

fun interface [Signable](index.md)

A component that can be signed into a [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md).

#### Inheritors

| |
|---|
| [UnsignedJwt](../../com.mooncloak.kodetools.kjwt.core/-unsigned-jwt/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [sign](sign.md) | [common]<br>abstract suspend fun [sign](sign.md)(resolver: [KeyResolver](../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), algorithm: [SignatureAlgorithm](../-signature-algorithm/index.md)): [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md)<br>Signs this [Jwt](../../com.mooncloak.kodetools.kjwt.core/-jwt/index.md) using the provided [algorithm](sign.md) and key to create a [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md). |
