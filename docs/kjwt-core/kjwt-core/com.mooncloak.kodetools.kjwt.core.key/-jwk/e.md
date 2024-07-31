//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[Jwk](index.md)/[e](e.md)

# e

[common]\
val [e](e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?

The &quot;e&quot; (exponent) parameter contains the exponent value for the RSA public key. It is represented as a Base64urlUInt-encoded value.

For instance, when representing the value 65537, the octet sequence to be base64url-encoded MUST consist of the three octets 1, 0, 1; the resulting representation for this value is &quot;AQAB&quot;.

#### See also

| | |
|---|---|
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.1.2) |
