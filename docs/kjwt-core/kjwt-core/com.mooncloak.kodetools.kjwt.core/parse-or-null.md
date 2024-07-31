//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core](index.md)/[parseOrNull](parse-or-null.md)

# parseOrNull

[common]\
suspend fun [Jws.Parser](-jws/-parser/index.md).[parseOrNull](parse-or-null.md)(compacted: [CompactedJwt](-compacted-jwt/index.md), json: Json = Json.Default, verifier: [Verifier](../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) = Verifier.Default, resolver: [KeyResolver](../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), validation: suspend [Jws](-jws/index.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { true }): [Jws](-jws/index.md)?

Parses the provided [compacted](parse-or-null.md) JWT instance into a [Jws](-jws/index.md) and returns the value, or `null` if the parsing failed.

#### Return

The parsed [Jws](-jws/index.md), or `null` if the parsing failed.

#### Parameters

common

| | |
|---|---|
| compacted | The [CompactedJwt](-compacted-jwt/index.md) instance to parse into a [Jws](-jws/index.md). |
| json | The Json instance to use for deserializing and serializing JSON models. |
| resolver | The [KeyResolver](../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md) used to obtain the signing key to verify the signature of the [Jws](-jws/index.md) represented by the provided [compacted](parse-or-null.md) JWT. |
| verifier | The [Verifier](../com.mooncloak.kodetools.kjwt.core.signature/-verifier/index.md) used to verify the signature. |
| validation | A function that performs additional validation on the parsed [Jws](-jws/index.md) instance. If this function returns `false`, then validation failed and an exception is thrown. Otherwise, if this function returns `true`, the [Jws](-jws/index.md) is returned from this [parse](parse.md) function. |
