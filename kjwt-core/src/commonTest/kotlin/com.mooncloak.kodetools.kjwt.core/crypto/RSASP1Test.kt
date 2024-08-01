@file:Suppress("TestFunctionName")

package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class RSASP1Test {

    @Test
    fun throws_exception_for_m_less_than_zero() {
        val n = BigInteger.fromInt(3233)
        val d = BigInteger.fromInt(413)
        val m = BigInteger.fromInt(-1)

        assertFails {
            RSASP1(n = n, d = d, m = m)
        }
    }

    @Test
    fun throws_exception_for_m_equal_to_n() {
        val n = BigInteger.fromInt(3233)
        val d = BigInteger.fromInt(413)
        val m = BigInteger.fromInt(3233)

        assertFails {
            RSASP1(n = n, d = d, m = m)
        }
    }

    @Test
    fun throws_exception_for_m_greater_than_n() {
        val n = BigInteger.fromInt(3233)
        val d = BigInteger.fromInt(413)
        val m = BigInteger.fromInt(3234)

        assertFails {
            RSASP1(n = n, d = d, m = m)
        }
    }

    @Test
    fun RSASP1_should_return_the_correct_signature_for_small_values() {
        val n = BigInteger.fromInt(3233)
        val d = BigInteger.fromInt(413)
        val m = BigInteger.fromInt(98)
        val expectedSignature = BigInteger.fromInt(2600)
        val actualSignature = RSASP1(n, d, m)

        assertEquals(expectedSignature, actualSignature)
    }

    @Test
    fun RSASP1_should_handle_zero_message() {
        val n = BigInteger.fromInt(3233)
        val d = BigInteger.fromInt(413)
        val m = BigInteger.ZERO
        val expectedSignature = BigInteger.ZERO
        val actualSignature = RSASP1(n, d, m)

        assertEquals(expectedSignature, actualSignature)
    }
}
