//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core](index.md)/[thumbprint](thumbprint.md)

# thumbprint

[common]\
fun [Jwk](../com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md).[thumbprint](thumbprint.md)(hashFunction: [HashFunction](../com.mooncloak.kodetools.kjwt.core.crypto/-hash-function/index.md) = HashFunction.Sha256): [Thumbprint](-thumbprint/index.md)

Creates a JSON Web Key Thumbprint according to the [specification](https://www.rfc-editor.org/rfc/rfc7638).

#### Parameters

common

| | |
|---|---|
| hashFunction | The [HashFunction](../com.mooncloak.kodetools.kjwt.core.crypto/-hash-function/index.md) used in the thumbprint calculation. Defaults to [HashFunction.Companion.Sha256](../com.mooncloak.kodetools.kjwt.core.crypto/-sha256.md). |

#### See also

| | |
|---|---|
|  | [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638) |
