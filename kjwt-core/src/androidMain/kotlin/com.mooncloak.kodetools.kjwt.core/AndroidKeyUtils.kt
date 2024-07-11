package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import com.mooncloak.kodetools.kjwt.key.KeyMaterial
import com.mooncloak.kodetools.kjwt.key.toKey
import io.jsonwebtoken.Jwts
import kotlinx.serialization.json.Json

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun Jwk.toKeyMaterial(json: Json): KeyMaterial = TODO()

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun KeyMaterial.toJwk(json: Json): Jwk = TODO()

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun SignatureAlgorithm.generateSigningKey(): KeyMaterial? {
    val result = when (this) {
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

    return when (result) {
        // FIXME: is java.security.Key -> com.mooncloak.kodetools.kjwt.key.toKey()
        // FIXME: is java.security.KeyPair -> result.toKeyPair()
        else -> error("Unexpected type generated for signing key $result.")
    }
}

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun SignatureAlgorithm.keyFrom(bytes: ByteArray): Key = TODO()
