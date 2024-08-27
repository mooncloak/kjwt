package com.mooncloak.kodetools.aes

/**
 * Implements the [BlockCipherMode.ECB] (Electronic Codebook Mode) using AES for the Cipher and Inverse Cipher to
 * encrypt and decrypt data. In this mode, the plaintext to be encrypted, is broken up into numerous blocks of the
 * supported size by AES, and each block is independently run through the Cipher function and the resulting sequence of
 * output blocks is the ciphertext. The encryption of each block can be performed in parallel.
 *
 * > [!Warning]
 * > The [BlockCipherMode.ECB] mode is considered insecure because each data block can be decrypted in parallel and
 * > patterns can be revealed in the plaintext. Consider using one of the other supported [BlockCipherMode]s for secure
 * > data.
 *
 * @see [Page 9 of Mode Documentation](https://nvlpubs.nist.gov/nistpubs/Legacy/SP/nistspecialpublication800-38a.pdf)
 */
public class AesEcb internal constructor(
    private val aesMode: AesMode
) {
}
