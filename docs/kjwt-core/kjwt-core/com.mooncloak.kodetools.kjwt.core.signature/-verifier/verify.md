//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[Verifier](index.md)/[verify](verify.md)

# verify

[common]\
abstract suspend fun [verify](verify.md)(signature: [Signature](../-signature/index.md), input: [SignatureInput](../-signature-input/index.md), key: [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md), algorithm: [SignatureAlgorithm](../-signature-algorithm/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Determines whether the provided [signature](verify.md) is valid for the provided signature [input](verify.md), [key](verify.md), and [algorithm](verify.md).

#### Return

`true` if the [signature](verify.md) is verified and valid, `false` otherwise.

#### Parameters

common

| | |
|---|---|
| signature | The [Signature](../-signature/index.md) to verify. |
| input | The [SignatureInput](../-signature-input/index.md) that was used to produce the [signature](verify.md). |
| key | The [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md) that will be used to verify the [signature](verify.md). |
| algorithm | The [SignatureAlgorithm](../-signature-algorithm/index.md) that was used to produce the [signature](verify.md). |

#### Throws

| | |
|---|---|
| [UnsupportedJwtSignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core/-unsupported-jwt-signature-algorithm/index.md) | if the provided [algorithm](verify.md) is not supported by this [Verifier](index.md). |
| [InvalidSignatureKeyAlgorithm](../../com.mooncloak.kodetools.kjwt.core/-invalid-signature-key-algorithm/index.md) | if the provided [key](verify.md) is not valid for the provided [algorithm](verify.md). |
