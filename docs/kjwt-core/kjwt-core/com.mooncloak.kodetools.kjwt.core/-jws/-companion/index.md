//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../../index.md)/[Jws](../index.md)/[Companion](index.md)

# Companion

[common]\
object [Companion](index.md) : [Jws.Parser](../-parser/index.md)

## Functions

| Name | Summary |
|---|---|
| [parse](../-parser/parse.md) | [common]<br>open suspend override fun [parse](../-parser/parse.md)(compacted: [CompactedJwt](../../-compacted-jwt/index.md), json: Json, verifier: [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md), resolver: [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), validation: suspend [Jws](../index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Jws](../index.md)<br>Parses the provided [compacted](../../../../../kjwt-core/com.mooncloak.kodetools.kjwt.core/-jws/-companion/parse.md) JWT instance into a [Jws](../index.md). |
| [parse](../../parse.md) | [common]<br>suspend fun [Jws.Parser](../-parser/index.md).[parse](../../parse.md)(compacted: [CompactedJwt](../../-compacted-jwt/index.md), resolver: [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), verifier: [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) = Verifier.Default, validation: suspend [Jws](../index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { true }): [Jws](../index.md)<br>Parses the provided [compacted](../../parse.md) JWT instance into a [Jws](../index.md). This is a convenience function that delegates to the [Jws.Parser.parse](../-parser/parse.md) function using the Json.Default instance. |
| [parseOrNull](../../parse-or-null.md) | [common]<br>suspend fun [Jws.Parser](../-parser/index.md).[parseOrNull](../../parse-or-null.md)(compacted: [CompactedJwt](../../-compacted-jwt/index.md), json: Json = Json.Default, verifier: [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) = Verifier.Default, resolver: [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), validation: suspend [Jws](../index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { true }): [Jws](../index.md)?<br>Parses the provided [compacted](../../parse-or-null.md) JWT instance into a [Jws](../index.md) and returns the value, or `null` if the parsing failed. |
