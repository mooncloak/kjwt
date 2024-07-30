package com.mooncloak.kodetools.kjwt.sample

import com.mooncloak.kodetools.kjwt.core.CompactedJwt
import com.mooncloak.kodetools.kjwt.core.Jwt
import com.mooncloak.kodetools.kjwt.core.invoke
import com.mooncloak.kodetools.kjwt.core.key.KeyResolver
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.signatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.datetime.Clock
import kotlinx.serialization.json.JsonPrimitive

@OptIn(ExperimentalJwtApi::class)
public suspend fun createJwt(
    keyResolver: KeyResolver,
    algorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
): CompactedJwt =
    Jwt {
        header {
            signatureAlgorithm = algorithm
            keyId = "MY_KEY_ID"
            // ...
        }

        payload {
            this.issuedAt = Clock.System.now()
            this["custom_claim"] = JsonPrimitive("CUSTOM_CLAIM_VALUE")
            this.putValue(key = "other_custom_claim", value = 0)
            // ...
        }
    }.sign(
        resolver = keyResolver,
        algorithm = algorithm
    ).compact()
