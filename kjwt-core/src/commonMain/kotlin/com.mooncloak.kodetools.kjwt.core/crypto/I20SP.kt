package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.math.pow

/**
 * Implements the I20SP conversion function from
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-4.1). This function converts the
 * provided [BigInteger] [x] into a [ByteArray].
 *
 * @param [x] A non-negative integer to be converted.
 *
 * @param [xLen] The intended length of the resulting octet string.
 *
 * @return A corresponding octet string of length [xLen].
 */
internal fun I20SP(
    x: BigInteger,
    xLen: Int
): ByteArray {
    @Suppress("NAME_SHADOWING")
    var x = x

    if (x >= 256.0.pow(xLen)) {
        error("integer too large")
    }

    val octets = ByteArray(xLen)

    for (i in 0 until xLen) {
        octets[i] = (x % 256).byteValue()
        x /= 256
    }

    return octets.reversedArray()
}
