package com.mooncloak.kodetools.aes

public class AesGcmCipher internal constructor(
    key: ByteArray,
    iv: ByteArray,
    override val operationMode: Cipher.OperationMode,
    private val aesMode: AesMode,
    private val paddingTransformer: Transformer
) : BlockCipher() {

    override val blockSize: Int
        get() = aesMode.blockSize

    private val roundKeys = Aes.expandKey(
        key = key,
        mode = aesMode
    )

    override suspend fun performNextBlock(input: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }

    override suspend fun pad(input: ByteArray): ByteArray =
        paddingTransformer.transform(input)
}

internal fun ghash(
    h: ByteArray,
    x: ByteArray
): ByteArray {
    require(x.size % 16 == 0) {
        "The bit string provided to the ghash function must be a multiple of 128-bits (16-bytes) in size."
    }

    val m = x.size / 16

    var y = ByteArray(size = 16)

    var i = 1
    while (i < m) {
        val xi = x.copyOfRange(
            fromIndex = i * 16,
            toIndex = (i * 16) + 16
        )

        y = (y xor xi).multiplyByH(h)

        i++
    }

    return y
}

internal fun gctr(
    initialCounterBlock: ByteArray,
    x: ByteArray,
    cipher: (input: ByteArray) -> ByteArray
): ByteArray {
    // GCTR Function defined by section 6.5 of the GCM specification, page 13.

    // Step 1.
    if (x.isEmpty()) return byteArrayOf()

    // Step 2.
    // Let n = ceil(len(X) / 128)
    // Note that 128 is the amount of bits, but we are working with bytes which are 8 bits long. So, we divide by 16
    // (128 bits) instead of 128.
    val n = x.size / 16

    // Step 3.
    // Let X1 , X2 , ... , Xn-1 , Xn* denote the unique sequence of bit strings ...

    // Step 4.
    // Let CB = ICB
    var cb = initialCounterBlock

    // Step 5.
    // For i = 2 to n, let CB = inc32(CB)
    // Note that we start at 1 here because the specification is 1-based with their arrays, whereas in Kotlin there is
    // zero-based arrays.
    var i = 1
    while (i < n) {
        cb = inc32(cb)
        i++
    }

    // Step 6.
    // For i = 1 to n - 1, let Y = X xor CIPH(cb)
    // Note that we start at 0 here because the specification is 1-based with their arrays, whereas in Kotlin there is
    // zero-based arrays.
    i = 0
    while (i < n - 1) {
        val xi = x.copyOfRange(
            fromIndex = i * 16,
            toIndex = (i * 16) + 16
        )
        val cbi = cb.copyOfRange(
            fromIndex = i * 16,
            toIndex = (i * 16) + 16
        )

        xi xor cipher(cbi)
    }

    // Step 7.



}

internal fun inc32(counterBlock: ByteArray): ByteArray {
    require(counterBlock.size == 16) {
        "Counter block must be 16 bytes long"
    }

    val result = counterBlock.copyOf()
    var counter = 0
    for (i in 15 downTo 12) {
        counter = (counter shl 8) or (result[i].toInt() and 0xff)
    }

    counter++

    for (i in 12..15) {
        result[i] = (counter and 0xff).toByte()
        counter = counter shr 8
    }

    return result
}
