//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[Verifier](index.md)

# Verifier

fun interface [Verifier](index.md)

Represents a component that can be used to verify that a [Signature](../-signature/index.md) is valid.

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
| [verify](verify.md) | [common]<br>abstract suspend fun [verify](verify.md)(signature: [Signature](../-signature/index.md), input: [SignatureInput](../-signature-input/index.md), key: [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md), algorithm: [SignatureAlgorithm](../-signature-algorithm/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determines whether the provided [signature](verify.md) is valid for the provided signature [input](verify.md), [key](verify.md), and [algorithm](verify.md). |
