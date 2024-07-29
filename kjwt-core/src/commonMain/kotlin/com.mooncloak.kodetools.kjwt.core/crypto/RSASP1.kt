package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger

/**
 * Implements the RSASP1 signature and verification primitive defined by
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1).
 *
 * @param [K] The [RsaPrivateKey] value.
 *
 * @param [m] message representative, an integer between 0 and n - 1.
 *
 * @return The signature representative, an integer between 0 and n - 1.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1)
 */
@Suppress("FunctionName", "LocalVariableName")
internal fun RSASP1(
    K: RsaPrivateKey,
    m: BigInteger
): BigInteger =
    when (K) {
        is RsaPrivateKey.Pair -> RSASP1(
            n = K.n,
            d = K.d,
            m = m
        )

        is RsaPrivateKey.MultiPrime -> RSASP1(
            p = K.p,
            q = K.q,
            dP = K.dP,
            dQ = K.dQ,
            qInv = K.qInv,
            triplets = K.additionalPrimes,
            m = m
        )
    }

/**
 * Implements the RSASP1 signature and verification primitive defined by
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1).
 *
 * @param [n] The RSA modulus which is used to derive the private key, K.
 *
 * @param [d] The RSA private exponent which is used to derive the private key, K.
 *
 * @param [m] message representative, an integer between 0 and n - 1.
 *
 * @return The signature representative, an integer between 0 and n - 1.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1)
 * @see [RSASP1] for a different version of this function where the private key, 'K', is
 * represented by values of 'p', 'q', 'dP', 'dQ', 'qInv', and other values.
 */
@Suppress("FunctionName")
private fun RSASP1(
    n: BigInteger,
    d: BigInteger,
    m: BigInteger
): BigInteger {
    // https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1

    // Step 1.
    if (m < 0 || m >= n) {
        error("message representative out of range")
    }

    // Step 2a.
    return m.pow(d) mod n
}

/**
 * Implements the RSASP1 signature and verification primitive defined by
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1).
 *
 * @param [p] The first prime factor of K.
 *
 * @param [q] The second prime factor of K.
 *
 * @param [dP] [p]'s CRT exponent, a positive integer such that `e * dP == 1 (mod (p-1))`.
 *
 * @param [dQ] [q]'s CRT exponent, a positive integer such that `e * dQ == 1 (mod (q-1))`.
 *
 * @param [qInv] CRT coefficient, a positive integer less than [p] such that `[q] * [qInv] == 1
 * (mod [p])`.
 *
 * @param [triplets] A sequence of triplets (r_i, d_i, t_i), i = 3, ..., u. The first value in the
 * [Triple] is 'r_i' which is prime factors of the RSA modulus 'n', including `r_1 = [p]`,
 * `r_2 = [q]`, and additional factors if any. The second value in the [Triple] is 'd_i' which is
 * an additional factor 'r_i's CRT exponent, a positive integer such that `e * d_i == 1 (mod
 * (r_i-1)), i = 3, ..., u`. The third and final value in the [Triple] is 't_i' which is an
 * additional prime factor 'r_i's CRT coefficient, a positive integer less than 'r_i' such that
 * `r_1 * r_2 * ... * r_(i-1) * t_i == 1 (mod r_i), i = 2, ..., u`.
 *
 * @param [m] message representative, an integer between 0 and n - 1.
 *
 * @return The signature representative, an integer between 0 and n - 1.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1)
 * @see [RSASP1] for a different version of this function where the private key, 'K', is
 * represented by values of 'n' and 'd'.
 */
@Suppress("FunctionName", "LocalVariableName")
private fun RSASP1(
    p: BigInteger,
    q: BigInteger,
    dP: BigInteger,
    dQ: BigInteger,
    qInv: BigInteger,
    triplets: List<AdditionalPrime> = emptyList(),
    m: BigInteger
): BigInteger {
    // https://www.rfc-editor.org/rfc/rfc8017#section-5.2.1

    // u - The number of prime factors of the RSA modulus, u >= 2.
    // There are always at least two factors, p and q, plus the additional factors provided by the
    // triplets.
    // https://www.rfc-editor.org/rfc/rfc8017#section-2
    val u = 2 + triplets.size
    val s = Array(u) { BigInteger.ZERO }

    // Step 2 - b - 1.
    // Let s_1 = m^dP mod p and s_2 = m^dQ mod q.
    s[0] = m.pow(dP) mod p
    s[1] = m.pow(dQ) mod q

    // Step 2 - b - 2.
    // If u > 2, let s_i = m^(d_i) mod r_i, i = 3, ..., u.
    var i = 2
    while (i < u) {
        val (r, d, _) = triplets[i - 2]

        s[i] = m.pow(d) mod r

        i++
    }

    // Step 2 - b - 3.
    // Let h = (s_1 - s_2) * qInv mod p.
    var h = (s[0] - s[1]) * qInv mod p

    // Step 2 - b - 4.
    // Let s = s_2 + q * h.
    // Here we are using 'result' instead of 's' as that would conflict with our representation of
    // 's_i'.
    var result = s[1] + q * h

    // Step 2 - b - 5.
    // If u > 2, let R = r_1 and for i = 3 to u do
    // Note that r_1 here would be 'p': "... including `r_1 = [p]`, `r_2 = [q]`"
    // The 'triplets' parameter should not include those values.
    var R = p

    var prevR = q
    i = 2
    while (i < u) {
        val (r, _, t) = triplets[i - 2]

        // Step 2 - b - 5 - a
        // Let R = R * r_(i-1).
        R *= prevR

        // Step 2 - b - 5 - b
        // Let h = (s_i - s) * t_i mod r_i.
        h = (s[i] - result) * t mod r

        // Step 2 - b - 5 - c
        // Let s = s + R * h.
        result += R * h

        prevR = r
        i++
    }

    return result
}
