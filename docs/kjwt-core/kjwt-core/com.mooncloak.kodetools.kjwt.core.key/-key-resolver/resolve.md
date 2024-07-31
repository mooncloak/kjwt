//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[KeyResolver](index.md)/[resolve](resolve.md)

# resolve

[common]\
abstract suspend fun [resolve](resolve.md)(header: [Header](../../com.mooncloak.kodetools.kjwt.core/-header/index.md), operation: [KeyOperation](../-key-operation/index.md)): [Jwk](../-jwk/index.md)?

Returns the signing key that should be used to validate a digital signature for the Claims JWS with the specified header and claims.

#### Return

the key that should be used to validate a digital signature for the Claims JWS with the specified header and claims, or `null` if no valid key was found.

#### Parameters

common

| | |
|---|---|
| header | the header of the JWS to validate |
| operation | The [KeyOperation](../-key-operation/index.md) that will be performed with the returned key. |
