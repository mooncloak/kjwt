//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../../index.md)/[Jws](../index.md)/[Parser](index.md)

# Parser

fun interface [Parser](index.md)

A component that parses and validates a [CompactedJwt](../../-compacted-jwt/index.md) and converts it into a [Jws](../index.md) and verifies its signature.

#### Inheritors

| |
|---|
| [Companion](../-companion/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | [common]<br>abstract suspend fun [parse](parse.md)(compacted: [CompactedJwt](../../-compacted-jwt/index.md), json: Json, verifier: [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md), resolver: [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), validation: suspend [Jws](../index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Jws](../index.md)<br>Parses the provided [compacted](parse.md) JWT instance into a [Jws](../index.md). |
| [parse](../../parse.md) | [common]<br>suspend fun [Jws.Parser](index.md).[parse](../../parse.md)(compacted: [CompactedJwt](../../-compacted-jwt/index.md), resolver: [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), verifier: [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) = Verifier.Default, validation: suspend [Jws](../index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { true }): [Jws](../index.md)<br>Parses the provided [compacted](../../parse.md) JWT instance into a [Jws](../index.md). This is a convenience function that delegates to the [Jws.Parser.parse](parse.md) function using the Json.Default instance. |
| [parseOrNull](../../parse-or-null.md) | [common]<br>suspend fun [Jws.Parser](index.md).[parseOrNull](../../parse-or-null.md)(compacted: [CompactedJwt](../../-compacted-jwt/index.md), json: Json = Json.Default, verifier: [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) = Verifier.Default, resolver: [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), validation: suspend [Jws](../index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { true }): [Jws](../index.md)?<br>Parses the provided [compacted](../../parse-or-null.md) JWT instance into a [Jws](../index.md) and returns the value, or `null` if the parsing failed. |
