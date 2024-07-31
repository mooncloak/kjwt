//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[CompactedJwt](index.md)

# CompactedJwt

@Serializable

@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)

value class [CompactedJwt](index.md)(val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

A typesafe class that represents a [Jwt](../-jwt/index.md) in its compacted [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) serialized form. For instance, if the [Jwt](../-jwt/index.md) is a secured [Jws](../-jws/index.md), then the [value](value.md) will take the following form:

```kotlin
Base64URL-Encoded-Header.Base64URL-Encoded-Payload.Base64URL-Encoded-Signature
```

#### See also

| |
|---|
| [Compactable](../-compactable/index.md) |
| [Jwt](../-jwt/index.md) |
| [Jws](../-jws/index.md) |
|  | [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519) |

## Constructors

| | |
|---|---|
| [CompactedJwt](-compacted-jwt.md) | [common]<br>constructor(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [common]<br>val [value](value.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
