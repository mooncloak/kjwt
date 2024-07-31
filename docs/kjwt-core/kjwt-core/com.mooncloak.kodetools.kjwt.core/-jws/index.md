//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[Jws](index.md)

# Jws

interface [Jws](index.md) : [Jwt](../-jwt/index.md), [Compactable](../-compactable/index.md)

Represents a JSON Web Signature (JWS), along with the decoded JSON Web Token (JWT) properties. This is an extension on the [Jwt](../-jwt/index.md) interface but provides the [signature](signature.md) value.

The Signature is a result of taking the encoded [Jwt.header](../-jwt/header.md) and [Jwt.payload](../-jwt/payload.md), adding a period character between them, and signing that, along with a secret key, using the signing algorithm specified in the Header. For example:

```kotlin
val signature = HMACSHA256(
    base64UrlEncode(header) + "." +
    base64UrlEncode(payload),
    secret)
```

A [Jws](index.md) provides integrity protection for a [Jwt](../-jwt/index.md). Because the Header and Payload of the JWT are signed by the creator of the token, it can easily be verified to make sure that the data was not tampered with. The signature algorithm can practically be any cryptographic signature algorithm, symmetric or asymmetric, but the natively supported options are defined by the [SignatureAlgorithm](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md) enum class.

A JWS takes the following encoded form:

```kotlin
Base64URL-Encoded-Header.Base64URL-Encoded-Payload.Base64URL-Encoded-Signature
```

In the absence of a signature (an &quot;unsecured&quot; JWT, see [Jws.isUnsecured](../is-unsecured.md)), a JWS takes the following encoded form:

```kotlin
Base64URL-Encoded-Header.Base64URL-Encoded-Payload.
```

#### See also

| | |
|---|---|
|  | [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519) |
| [Jwt](../-jwt/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) : [Jws.Parser](-parser/index.md) |
| [Parser](-parser/index.md) | [common]<br>fun interface [Parser](-parser/index.md)<br>A component that parses and validates a [CompactedJwt](../-compacted-jwt/index.md) and converts it into a [Jws](index.md) and verifies its signature. |

## Properties

| Name | Summary |
|---|---|
| [header](../-jwt/header.md) | [common]<br>abstract val [header](../-jwt/header.md): [Header](../-header/index.md)<br>The [Header](../-header/index.md) of this [Jwt](../-jwt/index.md) token. |
| [isSecured](../is-secured.md) | [common]<br>val [Jws](index.md).[isSecured](../is-secured.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determines whether this [Jws](index.md) is considered &quot;secured&quot; with a signature, which is the inverse of the [isUnsecured](../is-unsecured.md) value. |
| [isUnsecured](../is-unsecured.md) | [common]<br>val [Jws](index.md).[isUnsecured](../is-unsecured.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determines whether this [Jws](index.md) is considered an &quot;unsecured&quot; JWT, which, according to the [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-6), is a &quot;JWS using the &quot;alg&quot; Header Parameter value &quot;none&quot; and with the empty string for its JWS Signature value, as defined in the JWA specification.&quot; |
| [payload](../-jwt/payload.md) | [common]<br>abstract val [payload](../-jwt/payload.md): [Claims](../-claims/index.md)<br>The [Claims](../-claims/index.md) body of this [Jwt](../-jwt/index.md) token. |
| [signature](signature.md) | [common]<br>abstract val [signature](signature.md): [Signature](../../com.mooncloak.kodetools.kjwt.core.signature/-signature/index.md)<br>The signature represented as a Base64 URL encoded [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) of the signature [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html). |

## Functions

| Name | Summary |
|---|---|
| [compact](../-compactable/compact.md) | [common]<br>abstract suspend fun [compact](../-compactable/compact.md)(): [CompactedJwt](../-compacted-jwt/index.md)<br>Actually builds the JWT and serializes it to a compact, URL-safe string according to the [JWT Compact Serialization](https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-25#section-7) rules. |
| [component1](../component1.md) | [common]<br>operator fun [Jws](index.md).[component1](../component1.md)(): [Header](../-header/index.md)<br>Obtains the [Jws.header](../../../../kjwt-core/com.mooncloak.kodetools.kjwt.core/-jws/header.md) property in a deconstructed manner.<br>[common]<br>operator fun [Jwt](../-jwt/index.md).[component1](../component1.md)(): [Header](../-header/index.md)<br>Obtains the [Jwt.header](../-jwt/header.md) property in a deconstructed manner. |
| [component2](../component2.md) | [common]<br>operator fun [Jws](index.md).[component2](../component2.md)(): [Claims](../-claims/index.md)<br>Obtains the [Jws.payload](../../../../kjwt-core/com.mooncloak.kodetools.kjwt.core/-jws/payload.md) property in a deconstructed manner.<br>[common]<br>operator fun [Jwt](../-jwt/index.md).[component2](../component2.md)(): [Claims](../-claims/index.md)<br>Obtains the [Jwt.payload](../-jwt/payload.md) property in a deconstructed manner. |
| [component3](../component3.md) | [common]<br>operator fun [Jws](index.md).[component3](../component3.md)(): [Signature](../../com.mooncloak.kodetools.kjwt.core.signature/-signature/index.md)<br>Obtains the [Jws.signature](signature.md) property in a deconstructed manner. |
