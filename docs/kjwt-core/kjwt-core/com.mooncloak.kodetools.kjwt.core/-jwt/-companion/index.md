//[kjwt-core](../../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../../index.md)/[Jwt](../index.md)/[Companion](index.md)

# Companion

[common]\
object [Companion](index.md)

## Functions

| Name | Summary |
|---|---|
| [from](../../from.md) | [common]<br>fun [Jwt.Companion](index.md).[from](../../from.md)(header: [Header](../../-header/index.md), payload: [Claims](../../-claims/index.md), json: Json = Json.Default, signer: [Signer](../../../com.mooncloak.kodetools.kjwt.core.signature/-signer/index.md) = Signer.Default): [UnsignedJwt](../../-unsigned-jwt/index.md) |
| [invoke](../../invoke.md) | [common]<br>operator fun [Jwt.Companion](index.md).[invoke](../../invoke.md)(json: Json = Json.Default, builder: [Jwt.Builder](../-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [UnsignedJwt](../../-unsigned-jwt/index.md) |
