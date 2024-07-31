//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core](index.md)/[parse](parse.md)

# parse

[common]\
suspend fun [Jws.Parser](-jws/-parser/index.md).[parse](parse.md)(compacted: [CompactedJwt](-compacted-jwt/index.md), resolver: [KeyResolver](../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), verifier: [Verifier](../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) = Verifier.Default, validation: suspend [Jws](-jws/index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { true }): [Jws](-jws/index.md)

Parses the provided [compacted](parse.md) JWT instance into a [Jws](-jws/index.md). This is a convenience function that delegates to the [Jws.Parser.parse](-jws/-parser/parse.md) function using the Json.Default instance.

#### Return

The parsed [Jws](-jws/index.md).

#### Parameters

common

| | |
|---|---|
| compacted | The [CompactedJwt](-compacted-jwt/index.md) instance to parse into a [Jws](-jws/index.md). |
| resolver | The [KeyResolver](../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md) used to obtain the signing key to verify the signature of the [Jws](-jws/index.md) represented by the provided [compacted](parse.md) JWT. |
| verifier | The [Verifier](../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) used to verify the signature. |
| validation | A function that performs additional validation on the parsed [Jws](-jws/index.md) instance. If this function returns `false`, then validation failed and an exception is thrown. Otherwise, if this function returns `true`, the [Jws](-jws/index.md) is returned from this [parse](parse.md) function. |

#### Throws

| | |
|---|---|
| [JwtParseException](-jwt-parse-exception/index.md) | if an exception occurred during parsing. |
