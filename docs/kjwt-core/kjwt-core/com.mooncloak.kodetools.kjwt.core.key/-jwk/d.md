//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[Jwk](index.md)/[d](d.md)

# d

[common]\
val [d](d.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?

For elliptic curve private keys, the &quot;d&quot; (ECC private key) parameter contains the Elliptic Curve private key value. It is represented as the base64url encoding of the octet string representation of the private key value, as defined in Section 2.3.7 of SEC1 SEC1. The length of this octet string MUST be ceiling(log-base-2(n)/8) octets (where n is the order of the curve).

For rsa private keys, the &quot;d&quot; (private exponent) parameter contains the private exponent value for the RSA private key. It is represented as a Base64urlUInt-encoded value.

#### See also

| | |
|---|---|
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.1) |
