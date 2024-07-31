//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[Thumbprint](index.md)

# Thumbprint

@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)

@Serializable

value class [Thumbprint](index.md)(val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Represents a [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md) thumbprint value defined by the [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638). This class provides type safety for a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value representing a [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md) thumbprint, but is still performant since it is a value class.

#### See also

| | |
|---|---|
|  | [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638) |

## Constructors

| | |
|---|---|
| [Thumbprint](-thumbprint.md) | [common]<br>constructor(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [common]<br>val [value](value.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The actual [Jwk](../../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md) thumbprint [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value. |
