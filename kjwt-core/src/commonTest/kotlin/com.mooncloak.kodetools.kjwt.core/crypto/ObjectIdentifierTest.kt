package com.mooncloak.kodetools.kjwt.core.crypto

import kotlin.test.Test
import kotlin.test.assertContentEquals

class ObjectIdentifierTest {

    @Test
    fun toDerEncoded_small_value() {
        val value = 64
        val expectedEncoding = byteArrayOf(64)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_max_single_value() {
        val value = 127
        val expectedEncoding = byteArrayOf(127)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_min_variable_value() {
        val value = 128
        val expectedEncoding = byteArrayOf(-128, 1)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_large_value() {
        val value = 1024
        val expectedEncoding = byteArrayOf(-128, 8)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_very_large_value() {
        val value = 16384
        val expectedEncoding = byteArrayOf(-128, -128, 1)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }
}
