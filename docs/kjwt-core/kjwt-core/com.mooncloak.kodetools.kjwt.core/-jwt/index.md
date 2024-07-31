//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[Jwt](index.md)

# Jwt

interface [Jwt](index.md)

Represents a decoded JSON Web Token (JWT) without the Signature defined in a [Jws](../-jws/index.md). According to the [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-3), a JWT is either a [Jws](../-jws/index.md) or a JWE.

The [header](header.md) is a JSON object, which when encoded it is turned into a JSON [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) and Base64 URL encoded.

The [payload](payload.md) is also a JSON object, which when encoded, is turned into a JSON [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) and Base64 URL encoded.

This interface represents just the decoded [header](header.md) and [payload](payload.md) of a JWT, and not the Signature of a [Jws](../-jws/index.md), which is obtained after the other values are already encoded, or the extra parts of a JWE. For an extension of this interface, which includes the Signature, refer to the [Jws](../-jws/index.md) interface.

To obtain an instance of this interface, use the [Jwt](index.md) constructor function.

#### See also

| | |
|---|---|
|  | [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515) |
| jwt.io | (https://jwt.io/introduction/) |
| [Jws](../-jws/index.md) |

#### Inheritors

| |
|---|
| [Jws](../-jws/index.md) |
| [UnsignedJwt](../-unsigned-jwt/index.md) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [common]<br>class [Builder](-builder/index.md)<br>A builder component for creating a [Jwt](index.md) instance. |
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [header](header.md) | [common]<br>abstract val [header](header.md): [Header](../-header/index.md)<br>The [Header](../-header/index.md) of this [Jwt](index.md) token. |
| [payload](payload.md) | [common]<br>abstract val [payload](payload.md): [Claims](../-claims/index.md)<br>The [Claims](../-claims/index.md) body of this [Jwt](index.md) token. |

## Functions

| Name | Summary |
|---|---|
| [component1](../component1.md) | [common]<br>operator fun [Jwt](index.md).[component1](../component1.md)(): [Header](../-header/index.md)<br>Obtains the [Jwt.header](header.md) property in a deconstructed manner. |
| [component2](../component2.md) | [common]<br>operator fun [Jwt](index.md).[component2](../component2.md)(): [Claims](../-claims/index.md)<br>Obtains the [Jwt.payload](payload.md) property in a deconstructed manner. |
