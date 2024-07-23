package com.mooncloak.kodetools.kjwt.core.crypto

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class AlgorithmIdentifierTest {

    @Test
    fun expectedValues() {
        val values = AlgorithmIdentifier.entries

        assertEquals(expected = 9, actual = values.size)

        assertContains(values, AlgorithmIdentifier.MD2)
        assertContains(values, AlgorithmIdentifier.MD5)
        assertContains(values, AlgorithmIdentifier.SHA1)
        assertContains(values, AlgorithmIdentifier.SHA224)
        assertContains(values, AlgorithmIdentifier.SHA256)
        assertContains(values, AlgorithmIdentifier.SHA384)
        assertContains(values, AlgorithmIdentifier.SHA512)
        assertContains(values, AlgorithmIdentifier.SHA512_224)
        assertContains(values, AlgorithmIdentifier.SHA512_256)
    }

    @Test
    fun expectedAlgorithms() {
        assertContentEquals(
            expected = ObjectIdentifier.of(1, 2, 840, 113549, 2, 2).value,
            actual = AlgorithmIdentifier.MD2.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(1, 2, 840, 113549, 2, 5).value,
            actual = AlgorithmIdentifier.MD5.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(1, 3, 14, 3, 2, 26).value,
            actual = AlgorithmIdentifier.SHA1.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 4).value,
            actual = AlgorithmIdentifier.SHA224.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 1).value,
            actual = AlgorithmIdentifier.SHA256.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 2).value,
            actual = AlgorithmIdentifier.SHA384.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 3).value,
            actual = AlgorithmIdentifier.SHA512.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 5).value,
            actual = AlgorithmIdentifier.SHA512_224.algorithm.value
        )
        assertContentEquals(
            expected = ObjectIdentifier.of(2, 16, 840, 1, 101, 3, 4, 2, 6).value,
            actual = AlgorithmIdentifier.SHA512_256.algorithm.value
        )
    }

    @Test
    fun md2_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0C, // length 12
                0x06, // OID TAG
                0x08, // length 8
                0x2A,
                0x86.toByte(),
                0x48,
                0x86.toByte(),
                0xF7.toByte(),
                0x0D,
                0x02,
                0x02, // OID value (first part)
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.MD2.encode()
        )
    }

    @Test
    fun md5_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0C, // length 12
                0x06, // OID TAG
                0x08, // length 8
                0x2A,
                0x86.toByte(),
                0x48,
                0x86.toByte(),
                0xF7.toByte(),
                0x0D,
                0x02,
                0x05, // OID value (first part)
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.MD5.encode()
        )
    }

    @Test
    fun sha1_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x09, // length
                0x06, // OID TAG
                0x05, // length
                0x2B,
                0x0E,
                0x03,
                0x02,
                0x1A,
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.SHA1.encode()
        )
    }

    @Test
    fun sha224_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0d, // length
                0x06, // OID TAG
                0x09, // length
                0x60,
                0x86.toByte(),
                0x48,
                0x01,
                0x65,
                0x03,
                0x04,
                0x02,
                0x04,
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.SHA224.encode()
        )
    }

    @Test
    fun sha256_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0d, // length
                0x06, // OID TAG
                0x09, // length
                0x60,
                0x86.toByte(),
                0x48,
                0x01,
                0x65,
                0x03,
                0x04,
                0x02,
                0x01,
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.SHA256.encode()
        )
    }

    @Test
    fun sha384_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0d, // length
                0x06, // OID TAG
                0x09, // length
                0x60,
                0x86.toByte(),
                0x48,
                0x01,
                0x65,
                0x03,
                0x04,
                0x02,
                0x02,
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.SHA384.encode()
        )
    }

    @Test
    fun sha512_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0d, // length
                0x06, // OID TAG
                0x09, // length
                0x60,
                0x86.toByte(),
                0x48,
                0x01,
                0x65,
                0x03,
                0x04,
                0x02,
                0x03,
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.SHA512.encode()
        )
    }

    @Test
    fun sha512_224_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0d, // length
                0x06, // OID TAG
                0x09, // length
                0x60,
                0x86.toByte(),
                0x48,
                0x01,
                0x65,
                0x03,
                0x04,
                0x02,
                0x05,
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.SHA512_224.encode()
        )
    }

    @Test
    fun sha512_256_encodes_correctly() {
        // https://www.rfc-editor.org/rfc/rfc8017#section-9.2
        // Notes: 1.
        assertContentEquals(
            expected = byteArrayOf(
                0x30, // SEQUENCE TAG
                0x0d, // length
                0x06, // OID TAG
                0x09, // length
                0x60,
                0x86.toByte(),
                0x48,
                0x01,
                0x65,
                0x03,
                0x04,
                0x02,
                0x06,
                0x05, // NULL PARAMETERS
                0x00
            ),
            actual = AlgorithmIdentifier.SHA512_256.encode()
        )
    }
}
