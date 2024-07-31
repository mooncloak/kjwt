//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](../index.md)/[SignatureAlgorithm](index.md)

# SignatureAlgorithm

@Serializable

enum [SignatureAlgorithm](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[SignatureAlgorithm](index.md)&gt; 

Representation of standard JWT signature algorithm names as defined in the [JSON Web Algorithms Specification](https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-31).

Note that this class was adapted from the open source [JJWT library](https://github.com/jwtk/jjwt/blob/master/api/src/main/java/io/jsonwebtoken/SignatureAlgorithm.java).

!Note The [Jwt Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-8) only requires the [SignatureAlgorithm.HS256](-h-s256/index.md) and [SignatureAlgorithm.NONE](-n-o-n-e/index.md) to be supported, and recommends the [SignatureAlgorithm.RS256](-r-s256/index.md) and [SignatureAlgorithm.ES256](-e-s256/index.md) be supported. Therefore, the required signing algorithms are the only algorithms that are guaranteed to be supported across all platforms. Each platform may support any other [SignatureAlgorithm](index.md)s.

#### Parameters

common

| | |
|---|---|
| serialName | The JWA algorithm name constant. Should correspond to the defined values in the specification: https://datatracker.ietf.org/doc/html/rfc7518#section-3.1 |
| description | The JWA algorithm description. |
| family | The cryptographic family name of the signature algorithm. |
| jcaName | The name of the JCA algorithm used to compute the signature. |
| jdkStandard | Returns `true` if the algorithm is supported by standard JDK distributions or `false` if the algorithm implementation is not in the JDK and must be provided by a separate runtime JCA Provider (like BouncyCastle for example). This is useful for the JVM platform. |
| minKeyLength | Returns the minimum key length in bits (not bytes) that may be used with this algorithm according to the [JWT JWA Specification (RFC 7518)](https://tools.ietf.org/html/rfc7518). |

#### See also

| | |
|---|---|
|  | [jjwt library](https://github.com/jwtk/jjwt/blob/master/api/src/main/java/io/jsonwebtoken/SignatureAlgorithm.java) |

## Entries

| | |
|---|---|
| [NONE](-n-o-n-e/index.md) | [common]<br>@SerialName(value = &quot;none&quot;)<br>[NONE](-n-o-n-e/index.md)<br>JWA name for {@code No digital signature or MAC performed} |
| [HS256](-h-s256/index.md) | [common]<br>@SerialName(value = &quot;HS256&quot;)<br>[HS256](-h-s256/index.md)<br>JWA algorithm name for {@code HMAC using SHA-256} |
| [HS384](-h-s384/index.md) | [common]<br>@SerialName(value = &quot;HS384&quot;)<br>[HS384](-h-s384/index.md)<br>JWA algorithm name for {@code HMAC using SHA-384} |
| [HS512](-h-s512/index.md) | [common]<br>@SerialName(value = &quot;HS512&quot;)<br>[HS512](-h-s512/index.md)<br>JWA algorithm name for {@code HMAC using SHA-512} |
| [RS256](-r-s256/index.md) | [common]<br>@SerialName(value = &quot;RS256&quot;)<br>[RS256](-r-s256/index.md)<br>JWA algorithm name for {@code RSASSA-PKCS-v1_5 using SHA-256} |
| [RS384](-r-s384/index.md) | [common]<br>@SerialName(value = &quot;RS384&quot;)<br>[RS384](-r-s384/index.md)<br>JWA algorithm name for {@code RSASSA-PKCS-v1_5 using SHA-384} |
| [RS512](-r-s512/index.md) | [common]<br>@SerialName(value = &quot;RS512&quot;)<br>[RS512](-r-s512/index.md)<br>JWA algorithm name for {@code RSASSA-PKCS-v1_5 using SHA-512} |
| [ES256](-e-s256/index.md) | [common]<br>@SerialName(value = &quot;ES256&quot;)<br>[ES256](-e-s256/index.md)<br>JWA algorithm name for {@code ECDSA using P-256 and SHA-256} |
| [ES384](-e-s384/index.md) | [common]<br>@SerialName(value = &quot;ES384&quot;)<br>[ES384](-e-s384/index.md)<br>JWA algorithm name for {@code ECDSA using P-384 and SHA-384} |
| [ES512](-e-s512/index.md) | [common]<br>@SerialName(value = &quot;ES512&quot;)<br>[ES512](-e-s512/index.md)<br>JWA algorithm name for {@code ECDSA using P-521 and SHA-512} |
| [PS256](-p-s256/index.md) | [common]<br>@SerialName(value = &quot;PS256&quot;)<br>[PS256](-p-s256/index.md)<br>JWA algorithm name for {@code RSASSA-PSS using SHA-256 and MGF1 with SHA-256}.  <b>This algorithm requires Java 11 or later or a JCA provider like BouncyCastle to be in the runtime classpath.</b>  If on Java 10 or earlier, BouncyCastle will be used automatically if found in the runtime classpath. |
| [PS384](-p-s384/index.md) | [common]<br>@SerialName(value = &quot;PS384&quot;)<br>[PS384](-p-s384/index.md)<br>JWA algorithm name for {@code RSASSA-PSS using SHA-384 and MGF1 with SHA-384}.  <b>This algorithm requires Java 11 or later or a JCA provider like BouncyCastle to be in the runtime classpath.</b>  If on Java 10 or earlier, BouncyCastle will be used automatically if found in the runtime classpath. |
| [PS512](-p-s512/index.md) | [common]<br>@SerialName(value = &quot;PS512&quot;)<br>[PS512](-p-s512/index.md)<br>JWA algorithm name for {@code RSASSA-PSS using SHA-512 and MGF1 with SHA-512}. <b>This algorithm requires Java 11 or later or a JCA provider like BouncyCastle to be in the runtime classpath.</b>  If on Java 10 or earlier, BouncyCastle will be used automatically if found in the runtime classpath. |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |
| [Family](-family/index.md) | [common]<br>@Serializable<br>enum [Family](-family/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[SignatureAlgorithm.Family](-family/index.md)&gt; |

## Properties

| Name | Summary |
|---|---|
| [description](description.md) | [common]<br>val [description](description.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [digestLength](digest-length.md) | [common]<br>val [digestLength](digest-length.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [entries](entries.md) | [common]<br>val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[SignatureAlgorithm](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [family](family.md) | [common]<br>val [family](family.md): [SignatureAlgorithm.Family](-family/index.md) |
| [jcaName](jca-name.md) | [common]<br>val [jcaName](jca-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [jdkStandard](jdk-standard.md) | [common]<br>val [jdkStandard](jdk-standard.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [minKeyLength](min-key-length.md) | [common]<br>val [minKeyLength](min-key-length.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [name](-p-s512/index.md#-372974862%2FProperties%2F1883947000) | [common]<br>val [name](-p-s512/index.md#-372974862%2FProperties%2F1883947000): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [ordinal](-p-s512/index.md#-739389684%2FProperties%2F1883947000) | [common]<br>val [ordinal](-p-s512/index.md#-739389684%2FProperties%2F1883947000): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [pkcsName](pkcs-name.md) | [common]<br>val [pkcsName](pkcs-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [serialName](serial-name.md) | [common]<br>val [serialName](serial-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [isEllipticCurve](../is-elliptic-curve.md) | [common]<br>fun [SignatureAlgorithm](index.md).[isEllipticCurve](../is-elliptic-curve.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `true` if the enum instance represents an Elliptic Curve ECDSA signature algorithm, `false` otherwise. |
| [isHmac](../is-hmac.md) | [common]<br>fun [SignatureAlgorithm](index.md).[isHmac](../is-hmac.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `true` if the enum instance represents an HMAC signature algorithm, `false` otherwise. |
| [isRsa](../is-rsa.md) | [common]<br>fun [SignatureAlgorithm](index.md).[isRsa](../is-rsa.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `true` if the enum instance represents an RSA public/private key pair signature algorithm, `false` otherwise. |
| [valueOf](value-of.md) | [common]<br>fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [SignatureAlgorithm](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | [common]<br>fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[SignatureAlgorithm](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
