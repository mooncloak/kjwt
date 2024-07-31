//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../../index.md)/[Jwk](../index.md)/[Builder](index.md)/[thumbprintHashFunction](thumbprint-hash-function.md)

# thumbprintHashFunction

[common]\
var [thumbprintHashFunction](thumbprint-hash-function.md): [HashFunction](../../../com.mooncloak.kodetools.kjwt.core.crypto/-hash-function/index.md)

Determines the [HashFunction](../../../com.mooncloak.kodetools.kjwt.core.crypto/-hash-function/index.md) that is used to compute the [Jwk.thumbprint](../../../com.mooncloak.kodetools.kjwt.core/thumbprint.md) value when the [computeIdFromThumbprint](compute-id-from-thumbprint.md) is set to `true`. Defaults to [HashFunction.Companion.Sha256](../../../com.mooncloak.kodetools.kjwt.core.crypto/-sha256.md).
