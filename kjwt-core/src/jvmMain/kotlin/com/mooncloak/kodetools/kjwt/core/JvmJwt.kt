package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import io.jsonwebtoken.Jwts
import kotlinx.serialization.json.Json

@OptIn(ExperimentalKeyApi::class)
@ExperimentalJwtApi
internal class JvmJwt internal constructor(
    override val header: JvmHeader,
    override val payload: Claims
) : Jwt {

    override suspend fun compact(json: Json): CompactedJwt {
        var builder = Jwts.builder()
            .json(json)
            .header()
            .add(header.nativeHeader)
            .and()

        builder = when (payload) {
            is TextClaims -> builder.content(payload.value)

            is JvmJsonClaims -> builder.claims()
                .add(payload.nativeClaims)
                .and()

            is JsonClaims -> builder.claims()
                .add(payload)
                .and()
        }

        val jwtString = builder.compact()

        return CompactedJwt(value = jwtString)
    }

    override suspend fun sign(
        key: Key,
        algorithm: SignatureAlgorithm?,
        json: Json
    ): Jws {
        var builder = Jwts.builder()
            .json(json)
            .header()
            .add(header.nativeHeader)
            .and()

        builder = when (payload) {
            is TextClaims -> builder.content(payload.value)

            is JvmJsonClaims -> builder.claims()
                .add(payload.nativeClaims)
                .and()

            is JsonClaims -> builder.claims()
                .add(payload)
                .and()
        }

        val compactedString = builder.signWith(key.native)
            .compact()

        // The JWT library does not provide a direct way to convert to the JWS object. So, we have
        // to first create a compacted String value, then parse that value back into a JWS.
        val jws = Jwts.parser()
            .json(json)
            .keyLocator { key.native }
            .build()
            .parseSignedClaims(compactedString)

        return JvmJws(
            header = header,
            payload = payload,
            signature = Signature(value = jws.signature)
        )
    }
}

@ExperimentalJwtApi
internal class JvmJwtBuilder internal constructor(
    private val json: Json
) : Jwt.Builder() {

    private lateinit var headerValue: JvmHeader

    private lateinit var claimsValue: Claims

    override fun header(builder: Header.Builder.() -> Unit) {
        headerValue = Header.Builder(json = json).apply(builder).build(json)
    }

    override fun payload(builder: JsonClaims.Builder.() -> Unit) {
        claimsValue = JsonClaims.Builder(json = json).apply(builder).build(json)
    }

    override fun payload(claims: TextClaims) {
        claimsValue = claims
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JvmJwtBuilder) return false

        if (json != other.json) return false
        if (headerValue != other.headerValue) return false

        return claimsValue == other.claimsValue
    }

    override fun hashCode(): Int {
        var result = json.hashCode()
        result = 31 * result + headerValue.hashCode()
        result = 31 * result + claimsValue.hashCode()
        return result
    }

    override fun toString(): String =
        "JvmJwtBuilder(json=$json, header=$headerValue, body=$claimsValue)"

    internal fun build(): JvmJwt {
        if (!this::headerValue.isInitialized) {
            error("Cannot create a JWT. Header value is not initialized.")
        }
        if (!this::claimsValue.isInitialized) {
            error("Cannot create a JWT. Claims value is not initialized.")
        }

        return JvmJwt(
            header = headerValue,
            payload = claimsValue
        )
    }
}
