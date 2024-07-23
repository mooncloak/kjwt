package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.math.pow

/**
 * Implements the OS2ZIP conversion function from
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-4.2). This function converts the
 * provided [ByteArray] [X] into a [BigInteger].
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-4.2)
 */
@Suppress("FunctionName")
internal fun OS2IP(
    @Suppress("LocalVariableName") X: ByteArray
): BigInteger {
    var x = BigInteger.ZERO

    for (i in X.indices) {
        x += 256 * X[i].toDouble().pow(i).toLong()
    }

    return x
}
