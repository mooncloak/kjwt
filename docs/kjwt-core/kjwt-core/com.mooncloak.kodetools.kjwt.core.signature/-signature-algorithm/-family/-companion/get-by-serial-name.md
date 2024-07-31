//[kjwt-core](../../../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../../../index.md)/[SignatureAlgorithm](../../index.md)/[Family](../index.md)/[Companion](index.md)/[getBySerialName](get-by-serial-name.md)

# getBySerialName

[common]\
fun [getBySerialName](get-by-serial-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ignoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [SignatureAlgorithm.Family](../index.md)?

Retrieves the [SignatureAlgorithm.Family](../index.md) whose [SignatureAlgorithm.Family.serialName](../serial-name.md) property equals the provided [name](get-by-serial-name.md), optionally ignoring case sensitivity depending on the provided [ignoreCase](get-by-serial-name.md) value, or `null` if no value was found that matches.
