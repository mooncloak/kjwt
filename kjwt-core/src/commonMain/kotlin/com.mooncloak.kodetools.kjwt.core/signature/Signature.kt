package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.CompactedJwt
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.Jws
import com.mooncloak.kodetools.kjwt.core.util.encodeBase64UrlSafeWithoutPadding
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents the signature part of a [Jws].
 *
 * @property [value] The actual [ByteArray] signature value. Note that this value MAY be empty,
 * according to the specification, which can occur when a [Jws] is considered "unsecured" and uses
 * a signature algorithm of [SignatureAlgorithm.NONE].
 *
 * > [!Note]
 * > The [value] property represents the non-encoded signature value. When compacted into a
 * > [CompactedJwt], the [value] will have to be encoded (Base64 URL) according to the
 * > specification
 *
 * @see [Unsecured JWTs](https://datatracker.ietf.org/doc/html/rfc7519#section-6)
 * @see [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519)
 * @see [JWS Specification](https://datatracker.ietf.org/doc/html/rfc7515)
 * @see [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html)
 * @see [Jws]
 */
@Serializable
@JvmInline
@ExperimentalJwtApi
public value class Signature public constructor(
    public val value: ByteArray
) {

    /**
     * Creates a [Signature] value by UTF-8 encoding the provided [value] [String] into a
     * [ByteArray] to use as the [Signature.value] property.
     */
    public constructor(value: String) : this(value = value.encodeToByteArray())

    public companion object {

        /**
         * Represents an empty [Signature] value that is used for unsecured JWTs.
         */
        public val Empty: Signature = Signature(value = byteArrayOf())
    }
}

/**
 * Retrieves the Base64 Url encoded version of this [Signature.value].
 */
@ExperimentalJwtApi
public fun Signature.encoded(): String =
    this.value.encodeBase64UrlSafeWithoutPadding()
