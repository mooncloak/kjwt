//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[PrimeInfo](index.md)

# PrimeInfo

@Serializable

data class [PrimeInfo](index.md)(val r: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val d: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val t: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Represents a prime info object for the [Jwk.oth](../-jwk/oth.md) property defined by the [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.7).

#### See also

| | |
|---|---|
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.7) |

## Constructors

| | |
|---|---|
| [PrimeInfo](-prime-info.md) | [common]<br>constructor(r: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), d: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), t: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [PropertyKey](-property-key/index.md) | [common]<br>object [PropertyKey](-property-key/index.md) |

## Properties

| Name | Summary |
|---|---|
| [d](d.md) | [common]<br>@SerialName(value = &quot;d&quot;)<br>val [d](d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The &quot;d&quot; (factor CRT exponent) parameter within an &quot;oth&quot; array member represents the CRT exponent of the corresponding prime factor. It is represented as a Base64urlUInt-encoded value. |
| [r](r.md) | [common]<br>@SerialName(value = &quot;r&quot;)<br>val [r](r.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The &quot;r&quot; (prime factor) parameter within an &quot;oth&quot; array member represents the value of a subsequent prime factor. It is represented as a Base64urlUInt-encoded value. |
| [t](t.md) | [common]<br>@SerialName(value = &quot;t&quot;)<br>val [t](t.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The &quot;t&quot; (factor CRT coefficient) parameter within an &quot;oth&quot; array member represents the CRT coefficient of the corresponding prime factor. It is represented as a Base64urlUInt-encoded value. |
