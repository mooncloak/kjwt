package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.Claims
import com.mooncloak.kodetools.kjwt.core.Header
import com.mooncloak.kodetools.kjwt.core.JsonClaims
import com.mooncloak.kodetools.kjwt.core.TextClaims
import com.mooncloak.kodetools.kjwt.core.toJsonObject
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.util.encodeBase64UrlSafeWithoutPadding
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.jvm.JvmInline

/**
 * Represents the signature input for a [Signer].
 *
 * @property [value] The actual signature input [String] value. This takes the following form:
 * `ASCII(BASE64URL(UTF8(JWS Protected Header)) || '.' || BASE64URL(JWS Payload))`
 *
 * @see [Signer]
 * @see [RFC-7515](https://datatracker.ietf.org/doc/html/rfc7515#section-5)
 */
@JvmInline
@Serializable
@ExperimentalJwtApi
public value class SignatureInput internal constructor(
    public val value: String
) {

    /**
     * Creates a [SignatureInput] instance with the provided values, properly handling the encoding
     * and concatenation of the values.
     *
     * @param [header] The [Header] used for this signature input.
     *
     * @param [payload] The [Claims] used for this signature input.
     *
     * @param [json] The [Json] instance used for serialization of the [header] and [payload]
     * values.
     */
    public constructor(
        header: Header,
        payload: Claims,
        json: Json = Json.Default
    ) : this(value = buildString {
        val claimString = when (payload) {
            is TextClaims -> payload.value
            is JsonClaims -> json.encodeToString(
                serializer = JsonObject.serializer(),
                value = payload.toJsonObject()
            )
        }
        val encodedPayload = claimString.encodeToByteArray().encodeBase64UrlSafeWithoutPadding()
        val headerString = json.encodeToString(
            serializer = JsonObject.serializer(),
            value = header.toJsonObject()
        )
        val encodedHeader = headerString.encodeToByteArray().encodeBase64UrlSafeWithoutPadding()

        append("$encodedHeader.$encodedPayload")
    })

    public companion object
}
