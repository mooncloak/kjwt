package com.mooncloak.kodetools.kjwt.core

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import kotlinx.serialization.json.Json

@ExperimentalJwtApi
internal actual val DefaultJwtParser: Jwt.Parser
    get() = JvmJwtParser

@ExperimentalJwtApi
internal data object JvmJwtParser : Jwt.Parser {

    override suspend fun parse(
        compacted: CompactedJwt,
        json: Json
    ): Jwt {
        val jwt = try {
            Jwts.parser()
                .json(json)
                .build()
                .parse(compacted.value)
        } catch (e: JwtException) {
            throw JwtParseException(cause = e)
        }

        return JvmJwt(
            header = JvmHeader(
                json = json,
                nativeHeader = jwt.header
            ),
            payload = when (val payload = jwt.payload) {
                is ByteArray -> TextClaims(value = payload.decodeToString())

                is Claims -> JvmJsonClaims(
                    json = json,
                    nativeClaims = payload
                )

                else -> error("unexpected payload type $payload")
            }
        )
    }
}
