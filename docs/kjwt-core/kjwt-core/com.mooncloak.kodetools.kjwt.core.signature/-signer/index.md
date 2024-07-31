//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[Signer](index.md)

# Signer

fun interface [Signer](index.md)

Represents a component that can be used to produce a [Signature](../-signature/index.md).

#### See also

| | |
|---|---|
| [Default](../-default.md) | for a default implementation of this interface. |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [sign](sign.md) | [common]<br>abstract suspend fun [sign](sign.md)(input: [SignatureInput](../-signature-input/index.md), key: [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md), algorithm: [SignatureAlgorithm](../-signature-algorithm/index.md)): [Signature](../-signature/index.md)<br>Produces a [Signature](../-signature/index.md) value given the provided signature [input](sign.md), [key](sign.md), and [algorithm](sign.md). |
