package com.mooncloak.kodetools.aes

/**
 * A component that can transform input data into output data. This could be especially useful for adding padding to
 * the end of data so that it matches the appropriate block size required by the encryption or decryption algorithm.
 */
public fun interface Transformer {

    /**
     * Transforms the provided [input] bytes into the returned output [ByteArray].
     */
    public suspend fun transform(input: ByteArray): ByteArray

    public companion object
}

/**
 * Retrieves a [Transformer] implementation that performs no transformation on the input data and simply returns the
 * input data as-is.
 */
public val Transformer.Companion.NoTransform: Transformer
    get() = NoOpTransformer

/**
 * Retrieves a [Transformer] implementation that adds padding to the end of the input data according to the PKCS#7
 * specification using the provided [blockSize].
 *
 * @param [blockSize] The input data size of a single block. Defaults to `16` which is the block size for AES.
 */
@Suppress("FunctionName")
public fun Transformer.Companion.Pkcs7PaddingTransformer(blockSize: Int = 16): Transformer =
    Pkcs7PaddingTransformerImpl(blockSize = blockSize)

internal data object NoOpTransformer : Transformer {

    override suspend fun transform(input: ByteArray): ByteArray = input
}

internal class Pkcs7PaddingTransformerImpl internal constructor(
    private val blockSize: Int
) : Transformer {

    override suspend fun transform(input: ByteArray): ByteArray {
        val paddingLength = blockSize - (input.size % blockSize)
        val padding = ByteArray(paddingLength) { paddingLength.toByte() }

        return input + padding
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Pkcs7PaddingTransformerImpl) return false

        return blockSize == other.blockSize
    }

    override fun hashCode(): Int = blockSize

    override fun toString(): String = "Pkcs7PaddingTransformerImpl(blockSize=$blockSize)"
}
