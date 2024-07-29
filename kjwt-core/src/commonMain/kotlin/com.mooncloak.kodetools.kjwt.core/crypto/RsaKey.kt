package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.Sign
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.key.KeyType
import com.mooncloak.kodetools.kjwt.core.key.PrimeInfo
import com.mooncloak.kodetools.kjwt.core.key.build
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * A generic interface used to represent either a key or key-pair for the RSA algorithms.
 */
internal sealed interface RsaKeyMaterial

/**
 * Represents a pair of [RsaPublicKey] and [RsaPrivateKey].
 */
internal data class RsaKeyPair internal constructor(
    val publicKey: RsaPublicKey,
    val privateKey: RsaPrivateKey
) : RsaKeyMaterial

/**
 * Represents a key that is used with the RSA specification. RSA uses asymmetric keys, so there are
 * [RsaPublicKey] and [RsaPrivateKey]s.
 */
internal sealed interface RsaKey : RsaKeyMaterial

/**
 * Represents a public key used for the
 * [RSA Specification - RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-3.1).
 *
 * @property [n] the RSA modulus, a positive integer.
 *
 * @property [e] the RSA public exponent, a positive integer.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-3.1)
 */
internal data class RsaPublicKey internal constructor(
    val n: BigInteger,
    val e: BigInteger
) : RsaKey

/**
 * Represents a private key used for the
 * [RSA Specification - RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-3.2). This could
 * be represented as one of two classes: [Pair] or [MultiPrime].
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-3.2)
 */
internal sealed interface RsaPrivateKey : RsaKey {

    /**
     * The first representation of a [RsaPrivateKey] consists of the pair (n, d).
     *
     * @property [n] the RSA modulus, a positive integer.
     *
     * @property [d] the RSA private exponent, a positive integer.
     */
    class Pair internal constructor(
        val n: BigInteger,
        val d: BigInteger
    ) : RsaPrivateKey {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Pair) return false

            if (n != other.n) return false

            return d == other.d
        }

        override fun hashCode(): Int {
            var result = n.hashCode()
            result = 31 * result + d.hashCode()
            return result
        }

        override fun toString(): String = "RsaPrivateKey.Pair(REDACTED)"
    }

    /**
     * The second representation of a [RsaPrivateKey] consists of a quintuple (p, q, dP, dQ, qInv)
     * and a (possibly empty) sequence of triplets (r_i, d_i, t_i), i = 3, ..., u, one for each
     * prime not in the quintuple, where the components have the following meanings:
     *
     * @property [d] the RSA private exponent, a positive integer.
     *
     * @property [p] the first factor, a positive integer.
     *
     * @property [q] the second factor, a positive integer.
     *
     * @property [dP] the first factor's CRT exponent, a positive integer.
     *
     * @property [dQ] the second factor's CRT exponent, a positive integer.
     *
     * @property [qInv] the (first) CRT coefficient, a positive integer.
     *
     * @property [additionalPrimes] A sequence of [AdditionalPrime] triplets
     * `(r_i, d_i, t_i), i = 3, ..., u`. The first value in the [Triple] is 'r_i' which is prime
     * factors of the RSA modulus 'n', including `r_1 = [p]`, `r_2 = [q]`, and additional factors
     * if any. The second value in the [Triple] is 'd_i' which is an additional factor 'r_i's CRT
     * exponent, a positive integer such that `e * d_i == 1 (mod (r_i-1)), i = 3, ..., u`. The
     * third and final value in the [Triple] is 't_i' which is an additional prime factor 'r_i's
     * CRT coefficient, a positive integer less than 'r_i' such that
     * `r_1 * r_2 * ... * r_(i-1) * t_i == 1 (mod r_i), i = 2, ..., u`.
     */
    class MultiPrime internal constructor(
        val d: BigInteger? = null,
        val p: BigInteger,
        val q: BigInteger,
        val dP: BigInteger,
        val dQ: BigInteger,
        val qInv: BigInteger,
        val additionalPrimes: List<AdditionalPrime> = emptyList()
    ) : RsaPrivateKey {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is MultiPrime) return false

            if (d != other.d) return false
            if (p != other.p) return false
            if (q != other.q) return false
            if (dP != other.dP) return false
            if (dQ != other.dQ) return false
            if (qInv != other.qInv) return false

            return additionalPrimes == other.additionalPrimes
        }

        override fun hashCode(): Int {
            var result = p.hashCode()
            result = 31 * result + q.hashCode()
            result = 31 * result + d.hashCode()
            result = 31 * result + dP.hashCode()
            result = 31 * result + dQ.hashCode()
            result = 31 * result + qInv.hashCode()
            result = 31 * result + additionalPrimes.hashCode()
            return result
        }

        override fun toString(): String =
            "RsaPrivateKey.MultiPrime(REDACTED)"
    }
}

/**
 * Represents data on an additional factor for a [RsaPrivateKey.MultiPrime] value.
 *
 * @property [r] the prime factors of the RSA modulus 'n', a positive integer.
 *
 * @property [d] the CRT exponent of [r], a positive integer.
 *
 * @property [t] the CRT coefficient of [r], a positive integer.
 */
internal data class AdditionalPrime internal constructor(
    val r: BigInteger,
    val d: BigInteger,
    val t: BigInteger
)

@ExperimentalJwtApi
internal fun Jwk.toRsaKey(): RsaKey {
    require(this.keyType == KeyType.RSA) {
        "Only a JWK with a '${Jwk.PropertyKey.KEY_TYPE}' of '${KeyType.RSA.value}' can be converted into a RSA key. Key type provided was '${this.keyType.value}'."
    }

    val n = this.n?.decodeBase64UrlUInt()
    val e = this.e?.decodeBase64UrlUInt()
    val d = this.d?.decodeBase64UrlUInt()

    requireNotNull(n) { "Required JWK parameter '${Jwk.PropertyKey.N}' was not present." }
    requireNotNull(e) { "Reqiored JWK parameter '${Jwk.PropertyKey.E}' was not present." }

    // According to the JWA Specification (https://www.rfc-editor.org/rfc/rfc7518.html#section-6.3.2),
    // the "d" parameter is required for RSA private keys. So, if it is not present, then we can
    // consider this an RSA public key, otherwise, if it is present, we consider this an RSA
    // private key.
    if (d == null) {
        return RsaPublicKey(
            n = n,
            e = e
        )
    } else {
        val p = this.p?.decodeBase64UrlUInt()
        val q = this.q?.decodeBase64UrlUInt()
        val dP = this.dp?.decodeBase64UrlUInt()
        val dQ = this.dq?.decodeBase64UrlUInt()
        val qInv = this.qi?.decodeBase64UrlUInt()
        val additionalPrimes = this.oth?.map {
            AdditionalPrime(
                r = it.r.decodeBase64UrlUInt(),
                d = it.d.decodeBase64UrlUInt(),
                t = it.t.decodeBase64UrlUInt()
            )
        } ?: emptyList()

        if (
            p == null ||
            q == null ||
            dP == null ||
            dQ == null ||
            qInv == null
        ) {
            return RsaPrivateKey.Pair(
                n = n,
                d = d
            )
        } else {
            return RsaPrivateKey.MultiPrime(
                p = p,
                q = q,
                dP = dP,
                dQ = dQ,
                qInv = qInv,
                additionalPrimes = additionalPrimes
            )
        }
    }
}

@ExperimentalJwtApi
internal fun RsaKeyPair.toJwk(json: Json = Json.Default): Jwk {
    val keyPair = this

    return Jwk.build(
        keyType = KeyType.RSA,
        json = json
    ) {
        setKey(key = keyPair.publicKey)
        setKey(key = keyPair.privateKey)
    }
}

@ExperimentalJwtApi
internal fun Jwk.Builder.setKey(key: RsaKey) {
    when (key) {
        is RsaPublicKey -> {
            this.n = key.n.encodeBase64UrlUInt()
            this.e = key.e.encodeBase64UrlUInt()
        }

        is RsaPrivateKey.Pair -> {
            this.n = key.n.encodeBase64UrlUInt()
            this.d = key.d.encodeBase64UrlUInt()
        }

        is RsaPrivateKey.MultiPrime -> {
            this.d = key.d?.encodeBase64UrlUInt()
            this.p = key.p.encodeBase64UrlUInt()
            this.q = key.q.encodeBase64UrlUInt()
            this.dp = key.dP.encodeBase64UrlUInt()
            this.dq = key.dQ.encodeBase64UrlUInt()
            this.qi = key.qInv.encodeBase64UrlUInt()
            this.oth = key.additionalPrimes.map {
                PrimeInfo(
                    r = it.r.encodeBase64UrlUInt(),
                    d = it.d.encodeBase64UrlUInt(),
                    t = it.t.encodeBase64UrlUInt()
                )
            }.takeIf { it.isNotEmpty() }
        }
    }
}

@OptIn(ExperimentalEncodingApi::class)
internal fun String.decodeBase64UrlUInt(): BigInteger {
    val bytes = Base64.UrlSafe.decode(source = this)

    return BigInteger.fromByteArray(
        source = bytes,
        sign = Sign.POSITIVE
    )
}

@OptIn(ExperimentalEncodingApi::class)
internal fun BigInteger.encodeBase64UrlUInt(): String =
    Base64.UrlSafe.encode(source = this.toByteArray())
