//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[Compactable](index.md)/[compact](compact.md)

# compact

[common]\
abstract suspend fun [compact](compact.md)(): [CompactedJwt](../-compacted-jwt/index.md)

Actually builds the JWT and serializes it to a compact, URL-safe string according to the [JWT Compact Serialization](https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-25#section-7) rules.

This will return a [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value representing the encoded JWT with the following format:

```kotlin
Base64URL-Encoded-Header.Base64URL-Encoded-Payload.Base64URL-Encoded-Signature
```

Note that if this is invoked on a [Jwt](../-jwt/index.md) and not a subtype (ex: [Jws](../-jws/index.md)), then it will not include the trailing signature [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value. If this is a [Jws](../-jws/index.md) instance then it will include the trailing signature [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value.

#### Return

[CompactedJwt](../-compacted-jwt/index.md)

#### See also

| | |
|---|---|
|  | [JWT Specification](https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-25#section-7) |
