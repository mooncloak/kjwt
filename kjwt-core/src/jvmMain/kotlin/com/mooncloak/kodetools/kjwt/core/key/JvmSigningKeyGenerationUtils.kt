package com.mooncloak.kodetools.kjwt.core.key

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.util.fromTwosComplementByteArray
import com.mooncloak.kodetools.kjwt.core.crypto.AdditionalPrime
import com.mooncloak.kodetools.kjwt.core.crypto.RsaKeyPair
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPrivateKey
import com.mooncloak.kodetools.kjwt.core.crypto.RsaPublicKey
import java.security.KeyPairGenerator
import java.security.interfaces.RSAMultiPrimePrivateCrtKey
import java.security.interfaces.RSAPrivateCrtKey
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Suppress("unused")
internal actual suspend fun generateRsaKeyPair(bitCount: Int): RsaKeyPair? {
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
    )
}

internal fun java.math.BigInteger.toKotlinBigInteger(): BigInteger =
    BigInteger.fromTwosComplementByteArray(this.toByteArray())
