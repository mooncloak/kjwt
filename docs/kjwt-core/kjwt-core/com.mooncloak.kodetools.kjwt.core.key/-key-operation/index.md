//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core.key](../index.md)/[KeyOperation](index.md)

# KeyOperation

[common]\
@Serializable

@[JvmInline](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-inline/index.html)

value class [KeyOperation](index.md)(val value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Identifies the operations for which the key is intended to be used. Values defined by the specification are:

- 
   [Sign](-companion/-sign.md) (compute digital signature or MAC)
- 
   [Verify](-companion/-verify.md) (verify digital signature or MAC)
- 
   [Encrypt](-companion/-encrypt.md) (encrypt content)
- 
   [Decrypt](-companion/-decrypt.md) (decrypt content and validate decryption, if applicable)
- 
   [WrapKey](-companion/-wrap-key.md) (encrypt key)
- 
   [UnwrapKey](-companion/-unwrap-key.md) (decrypt key and validate decryption, if applicable)
- 
   [DeriveKey](-companion/-derive-key.md) (derive key)
- 
   [DeriveBits](-companion/-derive-bits.md) (derive bits not to be used as a key)

## Constructors

| | |
|---|---|
| [KeyOperation](-key-operation.md) | [common]<br>constructor(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [value](value.md) | [common]<br>val [value](value.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
