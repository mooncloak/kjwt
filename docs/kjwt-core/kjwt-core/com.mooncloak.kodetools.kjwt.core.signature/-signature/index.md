//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[Signature](index.md)

# Signature

@Serializable

@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)

value class [Signature](index.md)(val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Represents the signature part of a [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md).

#### See also

| | |
|---|---|
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html) |
| [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md) |

## Constructors

| | |
|---|---|
| [Signature](-signature.md) | [common]<br>constructor(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [common]<br>val [value](value.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The actual [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) signature value. Note that this value MAY be empty, according to the specification, which can occur when a [Jws](../../com.mooncloak.kodetools.kjwt.core/-jws/index.md) is considered &quot;unsecured&quot; and uses a signature algorithm of [SignatureAlgorithm.NONE](../-signature-algorithm/-n-o-n-e/index.md). |
