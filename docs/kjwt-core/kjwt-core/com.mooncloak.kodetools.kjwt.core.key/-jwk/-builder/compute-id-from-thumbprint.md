//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../../index.md)/[Jwk](../index.md)/[Builder](index.md)/[computeIdFromThumbprint](compute-id-from-thumbprint.md)

# computeIdFromThumbprint

[common]\
var [computeIdFromThumbprint](compute-id-from-thumbprint.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Whether the [Jwk.keyId](../key-id.md) value should be set to the computed [Jwk.thumbprint](../../../com.mooncloak.kodetools.kjwt.core/thumbprint.md) value. If `true`, when the [Jwk.Builder.build](build.md) function is invoked, the [Jwk.thumbprint](../../../com.mooncloak.kodetools.kjwt.core/thumbprint.md) function will be computed and its result will be set as the [Jwk.keyId](../key-id.md) value on the resulting [Jwk](../index.md). If `false`, no extra calculation will happen. Defaults to `false`.

Note that if this value is set to `true`, the [Jwk.thumbprint](../../../com.mooncloak.kodetools.kjwt.core/thumbprint.md) will override any previously set [Jwk.Builder.keyId](key-id.md) value.

It is a common practice to set the [Jwk.keyId](../key-id.md) as the thumbprint value, but is not mandatory.

#### See also

| | |
|---|---|
|  | [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638) |
