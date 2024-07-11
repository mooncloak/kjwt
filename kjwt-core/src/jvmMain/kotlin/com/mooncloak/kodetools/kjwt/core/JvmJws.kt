package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import io.jsonwebtoken.Jwts
import kotlinx.serialization.json.Json

@ExperimentalJwtApi
internal class JvmJws internal constructor(
    override val header: JvmHeader,
    override val payload: Claims,
    override val signature: Signature
) : Jws {

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

    @ExperimentalKeyApi
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
