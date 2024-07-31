//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../../index.md)/[Jwk](../index.md)/[PropertyKey](index.md)

# PropertyKey

[common]\
object [PropertyKey](index.md)

Jwk key values. This consists of the keys for the standard JWK properties, but other keys can be added via extension properties and functions.

## Properties

| Name | Summary |
|---|---|
| [ALGORITHM](-a-l-g-o-r-i-t-h-m.md) | [common]<br>const val [ALGORITHM](-a-l-g-o-r-i-t-h-m.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.algorithm](../algorithm.md) property. This constant value is equal to &quot;alg&quot;. |
| [CURVE](-c-u-r-v-e.md) | [common]<br>const val [CURVE](-c-u-r-v-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.curve](../curve.md) property. |
| [D](-d.md) | [common]<br>const val [D](-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.d](../d.md) property. |
| [DP](-d-p.md) | [common]<br>const val [DP](-d-p.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.dp](../dp.md) property. |
| [DQ](-d-q.md) | [common]<br>const val [DQ](-d-q.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.dq](../dq.md) property. |
| [E](-e.md) | [common]<br>const val [E](-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.e](../e.md) property. |
| [K](-k.md) | [common]<br>const val [K](-k.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.k](../k.md) property. |
| [KEY_ID](-k-e-y_-i-d.md) | [common]<br>const val [KEY_ID](-k-e-y_-i-d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.keyId](../key-id.md) property. This constant value is equal to &quot;kid&quot;. |
| [KEY_OPERATIONS](-k-e-y_-o-p-e-r-a-t-i-o-n-s.md) | [common]<br>const val [KEY_OPERATIONS](-k-e-y_-o-p-e-r-a-t-i-o-n-s.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.keyOperations](../key-operations.md) property. This constant value is equal to &quot;key_ops&quot;. |
| [KEY_TYPE](-k-e-y_-t-y-p-e.md) | [common]<br>const val [KEY_TYPE](-k-e-y_-t-y-p-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.keyType](../key-type.md) property. This constant value is equal to &quot;kty&quot;. |
| [N](-n.md) | [common]<br>const val [N](-n.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.n](../n.md) property. |
| [OTH](-o-t-h.md) | [common]<br>const val [OTH](-o-t-h.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.oth](../oth.md) property. |
| [P](-p.md) | [common]<br>const val [P](-p.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.p](../p.md) property. |
| [Q](-q.md) | [common]<br>const val [Q](-q.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.q](../q.md) property. |
| [QI](-q-i.md) | [common]<br>const val [QI](-q-i.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.qi](../qi.md) property. |
| [USE](-u-s-e.md) | [common]<br>const val [USE](-u-s-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.use](../use.md) property. This constant value is equal to &quot;use&quot;. |
| [X](-x.md) | [common]<br>const val [X](-x.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.x](../x.md) property. |
| [X5C](-x5-c.md) | [common]<br>const val [X5C](-x5-c.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.x5c](../x5c.md) property. This constant value is equal to &quot;x5c&quot;. |
| [X5T](-x5-t.md) | [common]<br>const val [X5T](-x5-t.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.x5t](../x5t.md) property. This constant value is equal to &quot;x5t&quot;. |
| [X5T_S256](-x5-t_-s256.md) | [common]<br>const val [X5T_S256](-x5-t_-s256.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.x5tS256](../x5t-s256.md) property. This constant value is equal to &quot;x5t#S256&quot;. |
| [X5U](-x5-u.md) | [common]<br>const val [X5U](-x5-u.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.x5u](../x5u.md) property. This constant value is equal to &quot;x5u&quot;. |
| [Y](-y.md) | [common]<br>const val [Y](-y.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The key for the [Jwk.y](../y.md) property. |
