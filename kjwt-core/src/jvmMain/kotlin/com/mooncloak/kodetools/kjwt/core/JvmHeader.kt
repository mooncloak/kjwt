package com.mooncloak.kodetools.kjwt.core

import io.jsonwebtoken.Jwts
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@ExperimentalJwtApi
internal fun Header.Builder.build(json: Json): JvmHeader {
    val jvmHeader = Jwts.header()
        .add(this)
        .build()

    return JvmHeader(
        json = json,
        nativeHeader = jvmHeader
    )
}

@ExperimentalJwtApi
internal class JvmHeader internal constructor(
    override val json: Json,
    internal val nativeHeader: io.jsonwebtoken.Header
) : BaseJwtObject(),
    Header {

    // io.jsonwebtoken.Header is a Map<String, Object> in Java. But we only store the
    // values as JsonElements, so convert them all over to JsonElements or throw an
    // exception.
    override val properties: Map<String, JsonElement> = nativeHeader.entries
        .map { entry ->
            entry.key to entry.value.toJsonElement()
        }.associate { pair ->
            pair.first to pair.second
        }

    override val algorithm: String?
        get() = nativeHeader.algorithm

    override val jwkSetUrl: String?
        get() = getValue(key = Header.PropertyKey.JWK_SET_URL)

    override val jwk: String?
        get() = getValue(key = Header.PropertyKey.JWK)

    override val keyId: String?
        get() = getValue(key = Header.PropertyKey.KEY_ID)

    override val x5u: String?
        get() = getValue(key = Header.PropertyKey.X5U)

    override val x5c: String?
        get() = getValue(key = Header.PropertyKey.X5C)

    override val x5t: String?
        get() = getValue(key = Header.PropertyKey.X5T)

    override val x5tS256: String?
        get() = getValue(key = Header.PropertyKey.X5T_S256)

    override val type: String?
        get() = nativeHeader.type

    override val contentType: String?
        get() = nativeHeader.contentType

    override val critical: String?
        get() = getValue(key = Header.PropertyKey.CRITICAL)

    override val compression: String?
        get() = nativeHeader.compressionAlgorithm

    override val encryption: String?
        get() = getValue(key = Header.PropertyKey.ENCRYPTION)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JvmHeader) return false

        if (json != other.json) return false
        if (nativeHeader != other.nativeHeader) return false

        return nativeHeader == other.nativeHeader
    }

    override fun hashCode(): Int {
        var result = json.hashCode()
        result = 31 * result + nativeHeader.hashCode()
        return result
    }

    override fun toString(): String =
        "JvmHeader(json=$json, nativeHeader=$nativeHeader)"
}
