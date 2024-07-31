//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../../index.md)/[SignatureAlgorithm](../index.md)/[Family](index.md)

# Family

[common]\
@Serializable

enum [Family](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[SignatureAlgorithm.Family](index.md)&gt;

## Entries

| | |
|---|---|
| [NONE](-n-o-n-e/index.md) | [common]<br>@SerialName(value = &quot;None&quot;)<br>[NONE](-n-o-n-e/index.md) |
| [RSA](-r-s-a/index.md) | [common]<br>@SerialName(value = &quot;RSA&quot;)<br>[RSA](-r-s-a/index.md) |
| [ECDSA](-e-c-d-s-a/index.md) | [common]<br>@SerialName(value = &quot;ECDSA&quot;)<br>[ECDSA](-e-c-d-s-a/index.md) |
| [HMAC](-h-m-a-c/index.md) | [common]<br>@SerialName(value = &quot;HMAC&quot;)<br>[HMAC](-h-m-a-c/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[SignatureAlgorithm.Family](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [name](../-p-s512/index.md#-372974862%2FProperties%2F1883947000) | [common]<br>val [name](../-p-s512/index.md#-372974862%2FProperties%2F1883947000): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](../-p-s512/index.md#-739389684%2FProperties%2F1883947000) | [common]<br>val [ordinal](../-p-s512/index.md#-739389684%2FProperties%2F1883947000): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [serialName](serial-name.md) | [common]<br>val [serialName](serial-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SignatureAlgorithm.Family](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[SignatureAlgorithm.Family](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
