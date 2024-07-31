//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[Jwk](index.md)/[n](n.md)

# n

[common]\
val [n](n.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?

The &quot;n&quot; (modulus) parameter contains the modulus value for the RSA public key. It is represented as a Base64urlUInt-encoded value.

Note that implementers have found that some cryptographic libraries prefix an extra zero-valued octet to the modulus representations they return, for instance, returning 257 octets for a 2048-bit key, rather than 256. Implementations using such libraries will need to take care to omit the extra octet from the base64url-encoded representation.

#### See also

| | |
|---|---|
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.1.1) |
