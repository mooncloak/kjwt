package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * A typesafe class that represents a [Jwt] in its compacted [String] serialized form. For
 * instance, if the [Jwt] is a secured [Jws], then the [value] will take the following form:
 *
 * ```
 * Base64URL-Encoded-Header.Base64URL-Encoded-Payload.Base64URL-Encoded-Signature
 * ```
 *
 * @see [Compactable]
 * @see [Jwt]
 * @see [Jws]
 * @see [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515)
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519)
 */
@Serializable
@JvmInline
@ExperimentalJwtApi
public value class CompactedJwt public constructor(
    public val value: String
)
