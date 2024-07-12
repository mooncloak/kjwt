package com.mooncloak.kodetools.kjwt.core

/**
 * A component that can be compacted into a [CompactedJwt] value.
 */
@ExperimentalJwtApi
public fun interface Compactable {

    /**
     * Actually builds the JWT and serializes it to a compact, URL-safe string according to the
     * [JWT Compact Serialization](https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-25#section-7) rules.
     *
     * This will return a [String] value representing the encoded JWT with the following format:
     *
     * ```
     * Base64URL-Encoded-Header.Base64URL-Encoded-Payload.Base64URL-Encoded-Signature
     * ```
     *
     * Note that if this is invoked on a [Jwt] and not a subtype (ex: [Jws]), then it will not
     * include the trailing signature [String] value. If this is a [Jws] instance then it will
     * include the trailing signature [String] value.
     *
     * @return [CompactedJwt]
     *
     * @see [JWT Specification](https://tools.ietf.org/html/draft-ietf-oauth-json-web-token-25#section-7)
     */
    public suspend fun compact(): CompactedJwt

    public companion object
}
