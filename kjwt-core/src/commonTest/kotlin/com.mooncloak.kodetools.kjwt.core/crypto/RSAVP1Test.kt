@file:Suppress("TestFunctionName")

package com.mooncloak.kodetools.kjwt.core.crypto

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class RSAVP1Test {

    @Test
    fun s_less_than_zero_should_throw_an_exception() {
        val publicKey = RsaPublicKey(
            n = BigInteger.fromInt(3233),
            e = BigInteger.fromInt(17)
        )

        assertFails {
            RSAVP1(publicKey = publicKey, s = BigInteger.fromInt(-1))
        }
    }

    @Test
    fun s_equal_to_n_should_throw_an_exception() {
        val publicKey = RsaPublicKey(
            n = BigInteger.fromInt(3233),
            e = BigInteger.fromInt(17)
        )

        assertFails {
            RSAVP1(publicKey = publicKey, s = BigInteger.fromInt(3233))
        }
    }

    @Test
    fun s_greater_than_n_should_throw_an_exception() {
        val publicKey = RsaPublicKey(
            n = BigInteger.fromInt(3233),
            e = BigInteger.fromInt(17)
        )

        assertFails {
            RSAVP1(publicKey = publicKey, s = BigInteger.fromInt(3234))
        }
    }

    @Test
    fun RSAVP1_should_return_the_correct_message_for_a_valid_signature() {
        val n = BigInteger.fromInt(3233)
        val e = BigInteger.fromInt(17)
        val publicKey = RsaPublicKey(n, e)
        val s = BigInteger.fromInt(2750) // Valid signature
        val expectedMessage = BigInteger.fromInt(1561)
        val actualMessage = RSAVP1(publicKey, s)

        assertEquals(expectedMessage, actualMessage)
    }
}
