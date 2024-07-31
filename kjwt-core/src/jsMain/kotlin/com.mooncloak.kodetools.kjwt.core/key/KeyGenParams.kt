package com.mooncloak.kodetools.kjwt.core.key

import org.khronos.webgl.Uint8Array

internal sealed external interface KeyGenParams {

    var name: String
}

// https://developer.mozilla.org/en-US/docs/Web/API/RsaHashedKeyGenParams
internal external interface RsaHashedKeyGenParams : KeyGenParams {

    /**
     * A [String]. This should be set to "RSASSA-PKCS1-v1_5", "RSA-PSS", or "RSA-OAEP", depending
     * on the algorithm you want to use.
     */
    override var name: String

    /**
     * A Number. The length in bits of the RSA modulus. This should be at least 2048: see for
     * example see
     * [SP 800-131A Rev. 2](https://csrc.nist.gov/publications/detail/sp/800-131a/rev-2/final).
     * Some organizations are now recommending that it should be 4096.
     */
    var modulusLength: Int

    /**
     * A Uint8Array. The public exponent. Unless you have a good reason to use something else,
     * specify 65537 here ([0x01, 0x00, 0x01]).
     */
    var publicExponent: Uint8Array

    /**
     * A string representing the name of the digest function to use. You can pass any of "SHA-256",
     * "SHA-384", or "SHA-512" here.
     */
    var hash: String
}

internal fun createRsaHashedKeyGenParams(
    name: String,
    modulusLength: Int,
    publicExponent: Uint8Array,
    hash: String
): RsaHashedKeyGenParams = createJsObject()
    .unsafeCast<RsaHashedKeyGenParams>()
    .apply {
        this.name = name
        this.modulusLength = modulusLength
        this.publicExponent = publicExponent
        this.hash = hash
    }
