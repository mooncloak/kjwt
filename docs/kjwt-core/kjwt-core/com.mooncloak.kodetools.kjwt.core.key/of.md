//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](index.md)/[of](of.md)

# of

[common]\
fun [KeyResolver.Companion](-key-resolver/-companion/index.md).[of](of.md)(key: [Jwk](-jwk/index.md)?): [KeyResolver](-key-resolver/index.md)

Retrieves a [KeyResolver](-key-resolver/index.md) instance that always resolves the provided [key](of.md) value. This is a convenience function.

#### Return

A [KeyResolver](-key-resolver/index.md) whose [KeyResolver.resolve](-key-resolver/resolve.md) function always returns the provided [key](of.md).

#### Parameters

common

| | |
|---|---|
| key | The [Jwk](-jwk/index.md) key that the returned [KeyResolver](-key-resolver/index.md) will always return. |

[common]\
fun [KeyResolver.Companion](-key-resolver/-companion/index.md).[of](of.md)(keys: [JwkSet](../com.mooncloak.kodetools.kjwt.core/-jwk-set/index.md)): [KeyResolver](-key-resolver/index.md)

Retrieves a [KeyResolver](-key-resolver/index.md) instance that chooses from [Jwk](-jwk/index.md)s defined in the provided [JwkSet](../com.mooncloak.kodetools.kjwt.core/-jwk-set/index.md) by comparing the [Jwk.keyId](-jwk/key-id.md) value with the [Header.keyId](../com.mooncloak.kodetools.kjwt.core/-header/key-id.md) value.

#### Return

A [KeyResolver](-key-resolver/index.md) whose [KeyResolver.resolve](-key-resolver/resolve.md) function returns a [Jwk](-jwk/index.md) from the provided [keys](of.md), or `null` if there is no match.

#### Parameters

common

| | |
|---|---|
| keys | The [JwkSet](../com.mooncloak.kodetools.kjwt.core/-jwk-set/index.md) to used to retrieve [Jwk](-jwk/index.md)s. |
