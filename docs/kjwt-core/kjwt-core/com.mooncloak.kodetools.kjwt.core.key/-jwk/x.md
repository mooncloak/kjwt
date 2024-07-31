//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[Jwk](index.md)/[x](x.md)

# x

[common]\
val [x](x.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?

The &quot;x&quot; (x coordinate) parameter contains the x coordinate for the Elliptic Curve point. It is represented as the base64url encoding of the octet string representation of the coordinate, as defined in Section 2.3.5 of SEC1 SEC1. The length of this octet string MUST be the full size of a coordinate for the curve specified in the &quot;crv&quot; parameter. For example, if the value of &quot;crv&quot; is &quot;P-521&quot;, the octet string must be 66 octets long.

#### See also

| | |
|---|---|
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.2.1.2) |
