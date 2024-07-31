//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core](index.md)/[signatureAlgorithm](signature-algorithm.md)

# signatureAlgorithm

[common]\
val [Header](-header/index.md).[signatureAlgorithm](signature-algorithm.md): [SignatureAlgorithm](../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md)?

Retrieves the [SignatureAlgorithm](../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md) algorithm used to sign the [Jws](-jws/index.md) token associated with this [Header](-header/index.md), or `null` if a matching [SignatureAlgorithm](../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md) could not be found or the [Header.algorithm](-header/algorithm.md) value was `null`.

[common]\
var [Header.Builder](-header/-builder/index.md).[signatureAlgorithm](signature-algorithm.md): [SignatureAlgorithm](../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md)?

Gets or sets the [SignatureAlgorithm](../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md) algorithm used to sign the [Jws](-jws/index.md) token associated with this [Header](-header/index.md), or `null` if a matching [SignatureAlgorithm](../com.mooncloak.kodetools.kjwt.core.signature/-signature-algorithm/index.md) could not be found or the [Header.algorithm](-header/algorithm.md) value was `null`.
