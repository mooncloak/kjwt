//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[Jwk](index.md)/[keyOperations](key-operations.md)

# keyOperations

[common]\
val [keyOperations](key-operations.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[KeyOperation](../-key-operation/index.md)&gt;?

The &quot;key_ops&quot; (key operations) parameter identifies the operation(s) for which the key is intended to be used. The &quot;key_ops&quot; parameter is intended for use cases in which public, private, or symmetric keys may be present.

Its value is an array of key operation values. Values defined by this specification are:

- 
   &quot;sign&quot; (compute digital signature or MAC)
- 
   &quot;verify&quot; (verify digital signature or MAC)
- 
   &quot;encrypt&quot; (encrypt content)
- 
   &quot;decrypt&quot; (decrypt content and validate decryption, if applicable)
- 
   &quot;wrapKey&quot; (encrypt key)
- 
   &quot;unwrapKey&quot; (decrypt key and validate decryption, if applicable)
- 
   &quot;deriveKey&quot; (derive key)
- 
   &quot;deriveBits&quot; (derive bits not to be used as a key)

#### See also

| | |
|---|---|
|  | [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517#section-4.3) |
