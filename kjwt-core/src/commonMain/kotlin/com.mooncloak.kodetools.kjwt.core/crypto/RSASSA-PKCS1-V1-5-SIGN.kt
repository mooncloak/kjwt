package com.mooncloak.kodetools.kjwt.core.crypto

/**
 * Implements the RSA (RSASSA-PKCS1-v1_5) signature generation operation defined by
 * [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-8.2.1).
 *
 * @param [K] Signer's RSA private key.
 *
 * @param [M] The message to be signed, an octet string.
 *
 * @param [hashAlgorithm] The unique hash algorithm identifier defined by the specification,
 * see [Appendix A.2.4](https://www.rfc-editor.org/rfc/rfc8017#appendix-A.2.4). Defaults to
 * [AlgorithmIdentifier.SHA256]. This is not defined as a parameter of the function in the
 * specification, but is provided to override the hash function used in the EMSA-PKCS1-v1_5-ENCODE
 * operation.
 *
 * @return The resulting signature, an octet string of length k, where k is the length in octets of
 * the RSA modulus n.
 *
 * @see [RFC-8017](https://www.rfc-editor.org/rfc/rfc8017#section-8.2.1)
 */
@Suppress("FunctionName", "LocalVariableName")
internal fun RSASSA_PKCS1_v1_5_SIGN(
    K: RsaPrivateKey,
    M: ByteArray,
    hashAlgorithm: AlgorithmIdentifier = AlgorithmIdentifier.SHA256
): ByteArray {
    // https://www.rfc-editor.org/rfc/rfc8017#section-8.2.1

    // Retrieve the length in octets of the RSA modulus, 'n', of the RSA private key.
    val k = K.k

    // Step 1.
    // EM = EMSA-PKCS1-V1_5-ENCODE (M, k).
    val EM = EmsaPkcs1V15Encoding.encode(
        M = M,
        emLen = k,
        hashAlgorithm = hashAlgorithm
    )

    // Step 2a.
    // m = OS2IP (EM).
    val m = OS2IP(EM)

    // Step 2b.
    // s = RSASP1 (K, m).
    val s = RSASP1(
        K = K,
        m = m
    )

    // Step 2c.
    // S = I2OSP (s, k).
    val S = I20SP(
        x = s,
        xLen = k
    )

    // Step 3.
    // Output the signature S.
    return S
}
