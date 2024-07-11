package com.mooncloak.kodetools.kjwt.core

import io.jsonwebtoken.Jwts
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@ExperimentalJwtApi
internal fun JsonClaims.Builder.build(json: Json): JvmJsonClaims {
    val jvmClaims = Jwts.claims()
        .add(this)
        .build()

    return JvmJsonClaims(
        json = json,
        nativeClaims = jvmClaims
    )
}

@ExperimentalJwtApi
internal class JvmJsonClaims internal constructor(
    override val json: Json,
    internal val nativeClaims: io.jsonwebtoken.Claims
) : BaseJwtObject(),
    JsonClaims {

    // io.jsonwebtoken.Header is a Map<String, Object> in Java. But we only store the
    // values as JsonElements, so convert them all over to JsonElements or throw an
    // exception.
    override val properties: Map<String, JsonElement> = nativeClaims.entries
        .map { entry ->
            entry.key to entry.value.toJsonElement()
        }.associate { pair ->
            pair.first to pair.second
        }

    override val id: String?
        get() = nativeClaims.id

    override val issuer: String?
        get() = nativeClaims.issuer

    override val subject: String?
        get() = nativeClaims.subject

    override val audience: Set<String>?
        get() = nativeClaims.audience

    override val expiration: Instant?
        get() = nativeClaims.expiration?.let { Instant.fromEpochMilliseconds(it.time) }

    override val notBefore: Instant?
        get() = nativeClaims.notBefore?.let { Instant.fromEpochMilliseconds(it.time) }

    override val issuedAt: Instant?
        get() = nativeClaims.issuedAt?.let { Instant.fromEpochMilliseconds(it.time) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JvmJsonClaims) return false

        if (json != other.json) return false

        return nativeClaims == other.nativeClaims
    }

    override fun hashCode(): Int {
        var result = json.hashCode()
        result = 31 * result + nativeClaims.hashCode()
        return result
    }

    override fun toString(): String =
        "JvmJsonClaims(json=$json, nativeClaims=$nativeClaims)"
}
