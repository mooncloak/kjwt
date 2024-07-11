package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

@ExperimentalJwtApi
internal actual val DefaultJwsParser: Jws.Parser
    get() = JvmJwsParser

@ExperimentalJwtApi
internal data object JvmJwsParser : Jws.Parser {

    @ExperimentalKeyApi
    override suspend fun parse(
        compacted: CompactedJwt,
        json: Json,
        resolver: KeyResolver
    ): Jws {
        val jws = try {
            Jwts.parser()
                .json(json)
                .keyLocator { header ->
                    runBlocking {// FIXME
                        resolver.resolve(
                            JvmHeader(
                                json = json,
                                nativeHeader = header
                            )
                        ).native
                    }
                }
                .build()
                .parseSignedClaims(compacted.value)
        } catch (e: JwtException) {
            throw JwtParseException(cause = e)
        }

        return JvmJws(
            header = JvmHeader(
                json = json,
                nativeHeader = jws.header
            ),
            payload = when (val payload = jws.payload) {
                is Claims -> JvmJsonClaims(
                    json = json,
                    nativeClaims = payload
                )

                else -> error("unexpected payload type $payload")
            },
            signature = Signature(value = jws.signature)
        )
    }
}
