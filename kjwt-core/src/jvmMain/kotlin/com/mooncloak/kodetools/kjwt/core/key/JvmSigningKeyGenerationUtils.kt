package com.mooncloak.kodetools.kjwt.core.key

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.util.fromTwosComplementByteArray
import com.mooncloak.kodetools.kjwt.core.crypto.AdditionalPrime
import com.mooncloak.kodetools.kjwt.core.crypto.RsaKeyPair
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPrivateKey
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPublicKey
import com.mooncloak.kodetools.kjwt.core.crypto.toJwk
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.json.Json
import java.security.KeyPairGenerator
import java.security.interfaces.RSAMultiPrimePrivateCrtKey
import java.security.interfaces.RSAPrivateCrtKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ExperimentalJwtApi
@Suppress("unused")
internal actual suspend fun generateRsaSigningKey(
    algorithm: SignatureAlgorithm,
    bitCount: Int,
    json: Json
): Jwk? {
    val generator = KeyPairGenerator.getInstance("RSA").apply {
        initialize(bitCount)
    }
    val keyPair = generator.generateKeyPair()
    val publicKey = keyPair.public as RSAPublicKey
    val privateKey = keyPair.private as RSAPrivateKey

    val n = publicKey.modulus.toKotlinBigInteger()
    val e = publicKey.publicExponent.toKotlinBigInteger()

    val rsaPrivateKey = when (privateKey) {
        is RSAPrivateCrtKey -> RsaPrivateKey.MultiPrime(
            p = privateKey.primeP.toKotlinBigInteger(),
            q = privateKey.primeQ.toKotlinBigInteger(),
            dP = privateKey.primeExponentP.toKotlinBigInteger(),
            dQ = privateKey.primeExponentQ.toKotlinBigInteger(),
            qInv = privateKey.crtCoefficient.toKotlinBigInteger(),
            n = n,
            d = privateKey.privateExponent?.toKotlinBigInteger()
        )

        is RSAMultiPrimePrivateCrtKey -> RsaPrivateKey.MultiPrime(
            p = privateKey.primeP.toKotlinBigInteger(),
            q = privateKey.primeQ.toKotlinBigInteger(),
            dP = privateKey.primeExponentP.toKotlinBigInteger(),
            dQ = privateKey.primeExponentQ.toKotlinBigInteger(),
            qInv = privateKey.crtCoefficient.toKotlinBigInteger(),
            n = n,
            d = privateKey.privateExponent?.toKotlinBigInteger(),
            additionalPrimes = privateKey.otherPrimeInfo.map { prime ->
                AdditionalPrime(
                    r = prime.prime.toKotlinBigInteger(),
                    d = prime.exponent.toKotlinBigInteger(),
                    t = prime.crtCoefficient.toKotlinBigInteger()
                )
            }
        )

        else -> RsaPrivateKey.Pair(
            n = n,
            d = privateKey.privateExponent.toKotlinBigInteger()
        )
    }

    return RsaKeyPair(
        publicKey = RsaPublicKey(
            n = n,
            e = e
        ),
        privateKey = rsaPrivateKey
    ).toJwk(json = json)
}

internal fun java.math.BigInteger.toKotlinBigInteger(): BigInteger =
    BigInteger.fromTwosComplementByteArray(this.toByteArray())
