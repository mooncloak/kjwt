package com.mooncloak.kodetools.aes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.jvm.JvmInline

public interface Cipher {

    public val operationMode: OperationMode

    /**
     * Retrieves a [SharedFlow] of all the [OutputData] from this [Cipher].
     *
     * @see [SharedFlow]
     */
    public fun outputFlow(): SharedFlow<OutputData>

    public suspend fun update(input: ByteArray)

    public suspend fun doFinal(input: ByteArray) {
        update(input = input)
        doFinal()
    }

    public suspend fun doFinal()

    public enum class OperationMode {

        Encrypt,
        Decrypt
    }

    public sealed interface OutputData {

        @JvmInline
        public value class BlockData public constructor(
            public val value: ByteArray
        ) : OutputData

        public data object EndMarker : OutputData
    }

    public companion object
}

/**
 * Retrieves a [Flow] of all the output [ByteArray] data from this cipher. This is a convenience function for invoking
 * the [Cipher.outputFlow] function, but filtering for the [Cipher.OutputData.BlockData] and mapping to the
 * [Cipher.OutputData.BlockData.value].
 */
public inline fun Cipher.outputDataFlow(): Flow<ByteArray> =
    this.outputFlow()
        .filterIsInstance<Cipher.OutputData.BlockData>()
        .map { it.value }

/**
 * Retrieves a [List] of the [ByteArray] data in order. This function will suspend until all the data has been
 * collected until the first [Cipher.OutputData.EndMarker] has been reached.
 */
public suspend fun Cipher.outputList(): List<ByteArray> {
    val result = mutableListOf<ByteArray>()
    var collecting = true

    supervisorScope {
        val job = launch {
            outputFlow()
                .collect { data ->
                    if (!collecting) return@collect

                    when (data) {
                        is Cipher.OutputData.BlockData -> result.add(data.value)
                        is Cipher.OutputData.EndMarker -> {
                            collecting = false
                            return@collect
                        }
                    }
                }
        }

        while (job.isActive) {
            if (!collecting) {
                job.cancel()
            }
        }
    }

    return result
}

/**
 * Retrieves the output data as a [ByteArray]. This function invokes the [outputList] and concatenates the values into
 * a single [ByteArray] using the [reduceOrNull] function. If the [outputList] function is empty, then an empty
 * [ByteArray] will be returned.
 *
 * @see [outputList]
 * @see [reduceOrNull]
 * @see [byteArrayOf]
 */
public suspend inline fun Cipher.output(): ByteArray =
    outputList().reduceOrNull { acc, bytes -> acc + bytes } ?: byteArrayOf()

public abstract class BlockCipher internal constructor() : Cipher {

    protected abstract val blockSize: Int

    private val mutex = Mutex(locked = false)
    private val output = MutableSharedFlow<Cipher.OutputData>()

    private var buffer = byteArrayOf()

    protected abstract suspend fun performNextBlock(
        input: ByteArray
    ): ByteArray

    protected abstract suspend fun pad(input: ByteArray): ByteArray

    override fun outputFlow(): SharedFlow<Cipher.OutputData> = output

    override suspend fun update(input: ByteArray) {
        mutex.withLock {
            val value = buffer + input
            val remainder = value.size % blockSize

            var i = 0
            while (i + blockSize < value.size) {
                val block = value.copyOfRange(
                    fromIndex = i,
                    toIndex = i + blockSize
                )

                val data = performNextBlock(input = block)

                output.emit(Cipher.OutputData.BlockData(value = data))

                i += blockSize
            }

            buffer = if (remainder == 0) {
                byteArrayOf()
            } else {
                value.copyOfRange(
                    fromIndex = i,
                    toIndex = value.size
                )
            }
        }
    }

    override suspend fun doFinal() {
        mutex.withLock {
            if (buffer.isNotEmpty()) {
                val value = pad(input = buffer)

                require(value.size % blockSize == 0) {
                    "Incorrect padding size value. block size: $blockSize; padded value size: ${value.size}"
                }

                var i = 0
                while (i + blockSize < value.size) {
                    val block = value.copyOfRange(
                        fromIndex = i,
                        toIndex = i + blockSize
                    )

                    val data = performNextBlock(input = block)

                    output.emit(Cipher.OutputData.BlockData(value = data))

                    i += blockSize
                }

                buffer = byteArrayOf()
            }

            output.emit(Cipher.OutputData.EndMarker)
        }
    }
}
