package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.key.KeyType
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.util.toJsonElement
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.DynamicJwkBuilder
import io.jsonwebtoken.security.Jwks
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import javax.crypto.SecretKey
import kotlin.coroutines.cancellation.CancellationException

@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
public suspend fun SignatureAlgorithm.generateSigningKey(json: Json): Jwk? {
    val key = when (this) {
        SignatureAlgorithm.NONE -> return null
        SignatureAlgorithm.HS256 -> Jwts.SIG.HS256.key().build()
        SignatureAlgorithm.HS384 -> Jwts.SIG.HS384.key().build()
        SignatureAlgorithm.HS512 -> Jwts.SIG.HS512.key().build()
        SignatureAlgorithm.RS256 -> Jwts.SIG.RS256.keyPair().build()
        SignatureAlgorithm.RS384 -> Jwts.SIG.RS384.keyPair().build()
        SignatureAlgorithm.RS512 -> Jwts.SIG.RS512.keyPair().build()
        SignatureAlgorithm.ES256 -> Jwts.SIG.ES256.keyPair().build()
        SignatureAlgorithm.ES384 -> Jwts.SIG.ES384.keyPair().build()
        SignatureAlgorithm.ES512 -> Jwts.SIG.ES512.keyPair().build()
        SignatureAlgorithm.PS256 -> Jwts.SIG.PS256.keyPair().build()
        SignatureAlgorithm.PS384 -> Jwts.SIG.PS384.keyPair().build()
        SignatureAlgorithm.PS512 -> Jwts.SIG.PS512.keyPair().build()
    }

    val jwk = when (key) {
        is java.security.Key -> Jwks.builder().key(key)
        is java.security.KeyPair -> Jwks.builder().pair(key)
        else -> error("Unexpected type generated for signing key $key.")
    }

    return jwk.toJwk(json = json)
}

@ExperimentalJwtApi
internal fun io.jsonwebtoken.security.Jwk<*>.toJwk(json: Json): Jwk {
    // The library only gives us access to a Map<String, Any>, so we have to convert that to a
    // Map<String, JsonElement> so that we can convert it to a Kotlin multiplatform JWK instance.
    val properties = this.entries.associate { entry ->
        entry.key to entry.value.toJsonElement()
    }

    return Jwk(
        json = json,
        properties = properties,
        keyType = KeyType(value = this.type)
    )
}

@ExperimentalJwtApi
internal fun Jwk.toJvmJwk(): io.jsonwebtoken.security.Jwk<*> {
    val jwkJsonString = this.json.encodeToString(
        serializer = JsonObject.serializer(),
        value = this.toJsonObject()
    )

    return Jwks.parser()
        .build()
        .parse(jwkJsonString)
}

internal fun DynamicJwkBuilder<*, *>.key(key: java.security.Key): io.jsonwebtoken.security.Jwk<*> =
    when (key) {
        is SecretKey -> key(key).build()
        is RSAPublicKey -> key(key).build()
        is RSAPrivateKey -> key(key).build()
        is ECPublicKey -> key(key).build()
        is ECPrivateKey -> key(key).build()
        is PublicKey -> key<PublicKey, PrivateKey>(key).build()
        is PrivateKey -> key<PublicKey, PrivateKey>(key).build()
        else -> error("Unsupported key $key.")
    }

internal fun DynamicJwkBuilder<*, *>.pair(keyPair: java.security.KeyPair): io.jsonwebtoken.security.Jwk<*> =
    keyPair<PublicKey, PrivateKey>(keyPair).build()
