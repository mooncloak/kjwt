package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger

/**
 * Represents a key that is used with the RSA specification. RSA uses asymmetric keys, so there are
 * [RsaPublicKey] and [RsaPrivateKey]s.
 */
internal sealed interface RsaKey

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
     * @property [triplets] A sequence of [AdditionalPrime] triplets
     * `(r_i, d_i, t_i), i = 3, ..., u`. The first value in the [Triple] is 'r_i' which is prime
     * factors of the RSA modulus 'n', including `r_1 = [p]`, `r_2 = [q]`, and additional factors
     * if any. The second value in the [Triple] is 'd_i' which is an additional factor 'r_i's CRT
     * exponent, a positive integer such that `e * d_i == 1 (mod (r_i-1)), i = 3, ..., u`. The
     * third and final value in the [Triple] is 't_i' which is an additional prime factor 'r_i's
     * CRT coefficient, a positive integer less than 'r_i' such that
     * `r_1 * r_2 * ... * r_(i-1) * t_i == 1 (mod r_i), i = 2, ..., u`.
     */
    class MultiPrime internal constructor(
        val p: BigInteger,
        val q: BigInteger,
        val dP: BigInteger,
        val dQ: BigInteger,
        val qInv: BigInteger,
        val triplets: List<AdditionalPrime> = emptyList()
    ) : RsaPrivateKey {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is MultiPrime) return false

            if (p != other.p) return false
            if (q != other.q) return false
            if (dP != other.dP) return false
            if (dQ != other.dQ) return false
            if (qInv != other.qInv) return false

            return triplets == other.triplets
        }

        override fun hashCode(): Int {
            var result = p.hashCode()
            result = 31 * result + q.hashCode()
            result = 31 * result + dP.hashCode()
            result = 31 * result + dQ.hashCode()
            result = 31 * result + qInv.hashCode()
            result = 31 * result + triplets.hashCode()
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
