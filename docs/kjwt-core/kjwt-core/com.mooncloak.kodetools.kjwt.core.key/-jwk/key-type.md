//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[Jwk](index.md)/[keyType](key-type.md)

# keyType

[common]\
val [keyType](key-type.md): [KeyType](../-key-type/index.md)

The &quot;kty&quot; (key type) parameter identifies the cryptographic algorithm family used with the key, such as &quot;RSA&quot; or &quot;EC&quot;. &quot;kty&quot; values should either be registered in the IANA &quot;JSON Web Key Types&quot; registry established by [JWA](https://datatracker.ietf.org/doc/html/rfc7518) or be a value that contains a Collision-Resistant Name. The &quot;kty&quot; value is a case-sensitive string. This member MUST be present in a JWK.

#### See also

| | |
|---|---|
|  | [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.1) |
