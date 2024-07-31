package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.khronos.webgl.Uint8Array

@ExperimentalJwtApi
internal actual suspend fun generateRsaSigningKey(
    algorithm: SignatureAlgorithm,
    bitCount: Int,
    json: Json
): Jwk? {
    val params = createRsaHashedKeyGenParams(
        name = "RSASSA-PKCS1-v1_5",
        modulusLength = bitCount,
        publicExponent = Uint8Array(arrayOf(0x01, 0x00, 0x01)),
        hash = when (algorithm) {
            SignatureAlgorithm.RS256 -> "SHA-256"
            SignatureAlgorithm.RS384 -> "SHA-384"
            SignatureAlgorithm.RS512 -> "SHA-512"
            else -> return null
        }
    )

    val key = window.crypto.subtle.generateKey(
        algorithm = params,
        extractable = true,
        keyUsages = listOf("sign", "verify")
    ).await()

    val jsJwk = window.crypto.subtle.exportKey(
        format = "jwk",
        key = key
    ).await()

    val jsonString = JSON.stringify(jsJwk)

    return json.decodeFromString(
        deserializer = Jwk.serializer(),
        string = jsonString
    )
}
