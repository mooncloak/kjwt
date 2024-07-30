package com.mooncloak.kodetools.kjwt.core.crypto

/**
 * Implements the RSA (RSASSA-PKCS1-v1_5) signature verification operation defined by
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-8.2.2).
 *
 * @param [publicKey] The signer's RSA public key.
 *
 * @param [M] The message whose signature is to be verified, an octet string.
 *
 * @param [S] The signature to be verified, an octet string of length k, where k is the length in
 * octets of the RSA modulus n.
 *
 * @param [hashAlgorithm] The unique hash algorithm identifier defined by the specification,
 * see [Appendix A.2.4](https://www.rfc-editor.org/rfc/rfc8017#appendix-A.2.4). Defaults to
 * [AlgorithmIdentifier.SHA256]. This is not defined as a parameter of the function in the
 * specification, but is provided to override the hash function used in the EMSA-PKCS1-v1_5-ENCODE
 * operation.
 *
 * @return `true` if the signature is valid, `false` if the signature is invalid.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-8.2.2)
 */
@Suppress("FunctionName", "LocalVariableName")
internal fun RSASSA_PKCS1_V1_5_VERIFY(
    publicKey: RsaPublicKey,
    M: ByteArray,
    S: ByteArray,
    hashAlgorithm: AlgorithmIdentifier = AlgorithmIdentifier.SHA256
): Boolean {
    // https://www.rfc-editor.org/rfc/rfc8017#section-8.2.2

    // Step 1.
    if (S.size != publicKey.k) {
        return false
    }

    // Step 2a.
    // s = OS2IP (S).
    val s = OS2IP(S)

    // Step 2b.
    // m = RSAVP1 ((n, e), s).
    val m = try {
        RSAVP1(
            publicKey = publicKey,
            s = s
        )
    } catch (_: Exception) {
        return false
    }

    // Step 2c.
    // EM = I2OSP (m, k).
    val EM = try {
        I20SP(
            x = m,
            xLen = publicKey.k
        )
    } catch (_: Exception) {
        return false
    }

    // Step 3.
    // EM' = EMSA-PKCS1-V1_5-ENCODE (M, k).
    val emPrime = EmsaPkcs1V15Encoding.encode(
        M = M,
        emLen = publicKey.k,
        hashAlgorithm = hashAlgorithm
    )

    // Step 4.
    return EM.contentEquals(emPrime)
}
