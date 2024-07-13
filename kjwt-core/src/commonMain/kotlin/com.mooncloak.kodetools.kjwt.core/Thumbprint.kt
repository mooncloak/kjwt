package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents a [Jwk] thumbprint value defined by the
 * [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638). This class provides type
 * safety for a [String] value representing a [Jwk] thumbprint, but is still performant since it is
 * a value class.
 *
 * @property [value] The actual [Jwk] thumbprint [String] value.
 *
 * @see [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638)
 */
@JvmInline
@Serializable
public value class Thumbprint public constructor(
    public val value: String
)
