package com.mooncloak.kodetools.kjwt.core.crypto

import kotlin.test.Test
import kotlin.test.assertContentEquals

// Note: some of these values were taken from the following resource (which is expected to be true):
// https://misc.daniel-marschall.de/asn.1/oid-converter/online.php
class ObjectIdentifierTest {

    @Test
    fun toDerEncoded_64() {
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
        val expectedEncoding = byteArrayOf(-127, 0)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_840() {
        val value = 840
        val expectedEncoding = byteArrayOf(-122, 72)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_1024() {
        val value = 1024
        val expectedEncoding = byteArrayOf(-120, 0)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_16384() {
        val value = 16384
        val expectedEncoding = byteArrayOf(-127, -128, 0)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }

    @Test
    fun toDerEncoded_113549() {
        val value = 113549
        val expectedEncoding = byteArrayOf(0x86.toByte(), 0xF7.toByte(), 0x0D)
        val actualEncoding = value.toDerEncoded().toByteArray()

        assertContentEquals(expectedEncoding, actualEncoding)
    }
}
