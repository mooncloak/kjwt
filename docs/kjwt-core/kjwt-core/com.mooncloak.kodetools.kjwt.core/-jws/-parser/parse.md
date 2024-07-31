//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../../index.md)/[Jws](../index.md)/[Parser](index.md)/[parse](parse.md)

# parse

[common]\
abstract suspend fun [parse](parse.md)(compacted: [CompactedJwt](../../-compacted-jwt/index.md), json: Json, verifier: [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md), resolver: [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), validation: suspend [Jws](../index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Jws](../index.md)

Parses the provided [compacted](parse.md) JWT instance into a [Jws](../index.md).

#### Return

The parsed [Jws](../index.md).

#### Parameters

common

| | |
|---|---|
| compacted | The [CompactedJwt](../../-compacted-jwt/index.md) instance to parse into a [Jws](../index.md). |
| json | The Json instance to use for deserializing and serializing JSON models. |
| verifier | The [Verifier](../../../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) used to verify the signature. |
| resolver | The [KeyResolver](../../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md) used to obtain the signing key to verify the signature of the [Jws](../index.md) represented by the provided [compacted](parse.md) JWT. |
| validation | A function that performs additional validation on the parsed [Jws](../index.md) instance. If this function returns `false`, then validation failed and an exception is thrown. Otherwise, if this function returns `true`, the [Jws](../index.md) is returned from this [parse](parse.md) function. |

#### Throws

| | |
|---|---|
| [JwtParseException](../../-jwt-parse-exception/index.md) | if an exception occurred during parsing. |
