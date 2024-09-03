package com.mooncloak.kodetools.aes

public class AesCbcCipher internal constructor(
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

    private var previousBlockCiphertext = iv

    override suspend fun performNextBlock(input: ByteArray): ByteArray =
        when (operationMode) {
            Cipher.OperationMode.Encrypt -> Aes.encryptBlock(
                numberOfRounds = aesMode.numberOfRounds,
                roundKeys = roundKeys,
                input = input xor previousBlockCiphertext
            ).apply {
                previousBlockCiphertext = this
            }

            Cipher.OperationMode.Decrypt -> {
                val output = Aes.decryptBlock(
                    numberOfRounds = aesMode.numberOfRounds,
                    roundKeys = roundKeys,
                    input = input
                )

                val plainText = output xor previousBlockCiphertext

                previousBlockCiphertext = input

                plainText
            }
        }

    override suspend fun pad(input: ByteArray): ByteArray =
        paddingTransformer.transform(input)
}
