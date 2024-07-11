package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import com.mooncloak.kodetools.kjwt.key.KeyMaterial
import com.mooncloak.kodetools.kjwt.key.KeyPair
import com.mooncloak.kodetools.kjwt.key.toKey
import com.mooncloak.kodetools.kjwt.key.toKeyPair
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.DynamicJwkBuilder
import io.jsonwebtoken.security.Jwks
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.PrivateJwk
import kotlinx.serialization.json.Json
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import javax.crypto.SecretKey

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun KeyMaterial.toJwk(json: Json): Jwk {
    val builder = when (this) {
        is Key -> Jwks.builder()
            .key(this.native)

        is KeyPair -> Jwks.builder()
            .keyPair<PublicKey, PrivateKey>(this.native)

        else -> error("Unexpected error converting KeyOrKeyPair to JWK.")
    }

    val javaJwk = builder.build()

    return Jwk(
        json = json,
        keyType = KeyType(value = javaJwk.type)
    ) {
        javaJwk.entries.forEach { entry ->
            put(entry.key, entry.value.toJsonElement())
        }
    }
}

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun Jwk.toKeyMaterial(json: Json): KeyMaterial {
    val jvmJwk = this.toJvmJwk(json)

    return if (jvmJwk is PrivateJwk<*, *, *>) {
        // Converts to a JWT library KeyPair, to a Java KeyPair, to our KeyPair model.
        jvmJwk.toKeyPair().toJavaKeyPair().toKeyPair()
    } else {
        jvmJwk.toKey().toKey()
    }
}

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
        is java.security.Key -> result.toKey()
        is java.security.KeyPair -> result.toKeyPair()
        else -> error("Unexpected type generated for signing key $result.")
    }
}

internal fun DynamicJwkBuilder<*, *>.key(key: java.security.Key) =
    when (key) {
        is SecretKey -> key(key)
        is RSAPublicKey -> key(key)
        is RSAPrivateKey -> key(key)
        is ECPublicKey -> key(key)
        is ECPrivateKey -> key(key)
        is PublicKey -> error("Use key pairs for public/private key values.")
        is PrivateKey -> error("Use key pairs for public/private key values.")
        else -> error("Unsupported key $key.")
    }

@ExperimentalJwtApi
internal fun Jwk.toJvmJwk(json: Json): io.jsonwebtoken.security.Jwk<*> {
    val jsonString = json.encodeToString(
        serializer = Jwk.serializer(),
        value = this
    )

    return Jwks.parser()
        .json(json)
        .build()
        .parse(jsonString)
}

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun SignatureAlgorithm.keyFrom(bytes: ByteArray): Key =
    when (this) {
        SignatureAlgorithm.HS256 -> Keys.hmacShaKeyFor(bytes).toKey()

        SignatureAlgorithm.HS384 -> Keys.hmacShaKeyFor(bytes).toKey()

        SignatureAlgorithm.HS512 -> Keys.hmacShaKeyFor(bytes).toKey()

        else -> error("Unsupported signature algorithm.")
    }
