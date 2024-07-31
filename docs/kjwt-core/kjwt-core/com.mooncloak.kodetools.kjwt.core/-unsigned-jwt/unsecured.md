//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[UnsignedJwt](index.md)/[unsecured](unsecured.md)

# unsecured

[common]\
fun [unsecured](unsecured.md)(): [Jws](../-jws/index.md)

Creates an &quot;unsecured&quot; [Jws](../-jws/index.md) instance. Note that this requires the [Header.signatureAlgorithm](../signature-algorithm.md) value to be equal to [SignatureAlgorithm.NONE](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/-n-o-n-e/index.md), otherwise an [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) will be thrown.

#### Return

An &quot;unsecured&quot; [Jws](../-jws/index.md), meaning that the signature value is empty.

#### See also

| |
|---|
| [isUnsecured](../is-unsecured.md) |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-state-exception/index.html) | if this [UnsignedJwt.header](header.md)'s [Header.signatureAlgorithm](../signature-algorithm.md) is NOT [SignatureAlgorithm.NONE](../../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/-n-o-n-e/index.md). |
