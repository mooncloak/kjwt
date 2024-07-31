//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[Signable](index.md)/[sign](sign.md)

# sign

[common]\
abstract suspend fun [sign](sign.md)(resolver: [KeyResolver](../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), algorithm: [SignatureAlgorithm](../-signature-algorithm/index.md)): [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md)

Signs this [Jwt](../../com.mooncloak.kodetools.kjwt.core/-jwt/index.md) using the provided [algorithm](sign.md) and key to create a [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md).

#### Return

The signed [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md).

#### Parameters

common

| | |
|---|---|
| resolver | The [KeyResolver](../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md) used to obtain the [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md) used for signing the JWT. Note that this value may be `null`, if the provided [algorithm](sign.md) is [SignatureAlgorithm.NONE](../-signature-algorithm/-n-o-n-e/index.md). If this value is `null`, in any other case, then an exception will be thrown. |
| algorithm | The [SignatureAlgorithm](../-signature-algorithm/index.md) used for signing the JWT. |

#### See also

| | |
|---|---|
| [SignatureAlgorithm](../-signature-algorithm/index.md) | for more information on the supported [SignatureAlgorithm](../-signature-algorithm/index.md)s. |

#### Throws

| | |
|---|---|
| [UnsupportedJwtSignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core/-unsupported-jwt-signature-algorithm/index.md) | if the provided [algorithm](sign.md) is unsupported by the platform, or the key obtained from the [resolver](sign.md) was required for the [algorithm](sign.md) but `null` was provided. |
