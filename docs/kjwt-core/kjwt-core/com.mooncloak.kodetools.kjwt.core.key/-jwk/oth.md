//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[Jwk](index.md)/[oth](oth.md)

# oth

[common]\
val [oth](oth.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[PrimeInfo](../-prime-info/index.md)&gt;?

The &quot;oth&quot; (other primes info) parameter contains an array of information about any third and subsequent primes, should they exist. When only two primes have been used (the normal case), this parameter MUST be omitted. When three or more primes have been used, the number of array elements MUST be the number of primes used minus two. For more information on this case, see the description of the OtherPrimeInfo parameters in Appendix A.1.2 of RFC 3447 RFC3447, upon which the following parameters are modeled. If the consumer of a JWK does not support private keys with more than two primes and it encounters a private key that includes the &quot;oth&quot; parameter, then it MUST NOT use the key.

#### See also

| | |
|---|---|
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2.7) |
