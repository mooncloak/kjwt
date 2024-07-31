//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[Compactable](index.md)

# Compactable

fun interface [Compactable](index.md)

A component that can be compacted into a [CompactedJwt](../-compacted-jwt/index.md) value.

#### Inheritors

| |
|---|
| [Jws](../-jws/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [compact](compact.md) | [common]<br>abstract suspend fun [compact](compact.md)(): [CompactedJwt](../-compacted-jwt/index.md)<br>Actually builds the JWT and serializes it to a compact, URL-safe string according to the [JWT Compact Serialization](https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-25#section-7) rules. |
