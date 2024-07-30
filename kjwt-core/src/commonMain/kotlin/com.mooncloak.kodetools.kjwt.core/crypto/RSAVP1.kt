package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger

/**
 * Implements the RSAVP1 signature verification primitive defined by
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.2).
 *
 * @param [publicKey] The RSA public key.
 *
 * @param [s] The signature representative, an integer between 0 and n - 1.
 *
 * @return The message representative, an integer between 0 and n - 1.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-5.2.2)
 */
@Suppress("FunctionName")
internal fun RSAVP1(
    publicKey: RsaPublicKey,
    s: BigInteger
): BigInteger {
    // https://www.rfc-editor.org/rfc/rfc8017#section-5.2.2

    // Step 1.
    if (s < 0 || s >= publicKey.n) {
        throw IllegalArgumentException("signature representative out of range")
    }

    // Step 2.
    val m = s.pow(publicKey.e) mod publicKey.n

    // Step 3.
    return m
}
