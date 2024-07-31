//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[Signer](index.md)/[sign](sign.md)

# sign

[common]\
abstract suspend fun [sign](sign.md)(input: [SignatureInput](../-signature-input/index.md), key: [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md), algorithm: [SignatureAlgorithm](../-signature-algorithm/index.md)): [Signature](../-signature/index.md)

Produces a [Signature](../-signature/index.md) value given the provided signature [input](sign.md), [key](sign.md), and [algorithm](sign.md).

#### Return

The resulting [Signature](../-signature/index.md) produced by signing the provided [input](sign.md) value using the provided [key](sign.md) for the provided signature [algorithm](sign.md).

#### Parameters

common

| | |
|---|---|
| input | The [SignatureInput](../-signature-input/index.md) that will be signed, resulting in the returned [Signature](../-signature/index.md). |
| key | The [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md) that will be used as a signing key. |
| algorithm | The [SignatureAlgorithm](../-signature-algorithm/index.md) to use. |

#### Throws

| | |
|---|---|
| [UnsupportedJwtSignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core/-unsupported-jwt-signature-algorithm/index.md) | if the provided [algorithm](sign.md) is not supported by this [Signer](index.md). |
| [InvalidSignatureKeyAlgorithm](../../com.mooncloak.kodetools.kjwt.core/-invalid-signature-key-algorithm/index.md) | if the provided [key](sign.md) is not valid for the provided [algorithm](sign.md). |
