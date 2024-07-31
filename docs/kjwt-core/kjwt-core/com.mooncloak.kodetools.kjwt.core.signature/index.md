//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core.signature](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Signable](-signable/index.md) | [common]<br>fun interface [Signable](-signable/index.md)<br>A component that can be signed into a [Jws](../com.mooncloak.kodetools.kjwt.core/-jws/index.md). |
| [Signature](-signature/index.md) | [common]<br>@Serializable<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>value class [Signature](-signature/index.md)(val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Represents the signature part of a [Jws](../com.mooncloak.kodetools.kjwt.core/-jws/index.md). |
| [SignatureAlgorithm](-signature-algorithm/index.md) | [common]<br>@Serializable<br>enum [SignatureAlgorithm](-signature-algorithm/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[SignatureAlgorithm](-signature-algorithm/index.md)&gt; <br>Representation of standard JWT signature algorithm names as defined in the [JSON Web Algorithms Specification](https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-31). |
| [SignatureInput](-signature-input/index.md) | [common]<br>@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)<br>@Serializable<br>value class [SignatureInput](-signature-input/index.md)(val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Represents the signature input for a [Signer](-signer/index.md). |
| [Signer](-signer/index.md) | [common]<br>fun interface [Signer](-signer/index.md)<br>Represents a component that can be used to produce a [Signature](-signature/index.md). |
| [Verifier](-verifier/index.md) | [common]<br>fun interface [Verifier](-verifier/index.md)<br>Represents a component that can be used to verify that a [Signature](-signature/index.md) is valid. |

## Properties

| Name | Summary |
|---|---|
| [Default](-default.md) | [common]<br>val [Signer.Companion](-signer/-companion/index.md).[Default](-default.md): [Signer](-signer/index.md)<br>Retrieves the platform default [Signer](-signer/index.md) implementation.<br>[common]<br>val [Verifier.Companion](-verifier/-companion/index.md).[Default](-default.md): [Verifier](-verifier/index.md)<br>Retrieves the platform default [Verifier](-verifier/index.md) implementation. |

## Functions

| Name | Summary |
|---|---|
| [isEllipticCurve](is-elliptic-curve.md) | [common]<br>fun [SignatureAlgorithm](-signature-algorithm/index.md).[isEllipticCurve](is-elliptic-curve.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `true` if the enum instance represents an Elliptic Curve ECDSA signature algorithm, `false` otherwise. |
| [isHmac](is-hmac.md) | [common]<br>fun [SignatureAlgorithm](-signature-algorithm/index.md).[isHmac](is-hmac.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `true` if the enum instance represents an HMAC signature algorithm, `false` otherwise. |
| [isRsa](is-rsa.md) | [common]<br>fun [SignatureAlgorithm](-signature-algorithm/index.md).[isRsa](is-rsa.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns `true` if the enum instance represents an RSA public/private key pair signature algorithm, `false` otherwise. |
