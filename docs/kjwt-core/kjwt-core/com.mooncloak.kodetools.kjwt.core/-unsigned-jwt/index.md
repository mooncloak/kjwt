//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[UnsignedJwt](index.md)

# UnsignedJwt

[common]\
class [UnsignedJwt](index.md) : [Jwt](../-jwt/index.md), [Signable](../../com.mooncloak.kodetools.kjwt.core.signature/-signable/index.md)

## Properties

| Name | Summary |
|---|---|
| [header](header.md) | [common]<br>open override val [header](header.md): [Header](../-header/index.md)<br>The [Header](../-header/index.md) of this [Jwt](../-jwt/index.md) token. |
| [payload](payload.md) | [common]<br>open override val [payload](payload.md): [Claims](../-claims/index.md)<br>The [Claims](../-claims/index.md) body of this [Jwt](../-jwt/index.md) token. |

## Functions

| Name | Summary |
|---|---|
| [component1](../component1.md) | [common]<br>operator fun [Jwt](../-jwt/index.md).[component1](../component1.md)(): [Header](../-header/index.md)<br>Obtains the [Jwt.header](../-jwt/header.md) property in a deconstructed manner. |
| [component2](../component2.md) | [common]<br>operator fun [Jwt](../-jwt/index.md).[component2](../component2.md)(): [Claims](../-claims/index.md)<br>Obtains the [Jwt.payload](../-jwt/payload.md) property in a deconstructed manner. |
| [sign](sign.md) | [common]<br>open suspend override fun [sign](sign.md)(resolver: [KeyResolver](../../com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md), algorithm: [SignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md)): [Jws](../-jws/index.md)<br>Signs this [Jwt](../-jwt/index.md) using the provided [algorithm](sign.md) and key to create a [Jws](../-jws/index.md). |
| [unsecured](unsecured.md) | [common]<br>fun [unsecured](unsecured.md)(): [Jws](../-jws/index.md)<br>Creates an &quot;unsecured&quot; [Jws](../-jws/index.md) instance. Note that this requires the [Header.signatureAlgorithm](../signature-algorithm.md) value to be equal to [SignatureAlgorithm.NONE](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/-n-o-n-e/index.md), otherwise an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) will be thrown. |
