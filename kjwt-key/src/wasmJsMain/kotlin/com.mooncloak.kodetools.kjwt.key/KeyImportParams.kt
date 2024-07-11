package com.mooncloak.kodetools.kjwt.key

public sealed external interface KeyImportParams : JsAny {

    public var name: String
}

/**
 * Represents the object that should be passed as the algorithm parameter into
 * SubtleCrypto.importKey() or SubtleCrypto.unwrapKey(), when importing any RSA-based key pair:
 * that is, when the algorithm is identified as any of RSASSA-PKCS1-v1_5, RSA-PSS, or RSA-OAEP.
 *
 * @see [RsaHashedImportParams](https://developer.mozilla.org/en-US/docs/Web/API/RsaHashedImportParams)
 */
public external interface RsaHashedImportParams : KeyImportParams {

    /**
     * A string. This should be set to RSASSA-PKCS1-v1_5, RSA-PSS, or RSA-OAEP, depending on the
     * algorithm you want to use.
     */
    public override var name: String

    /**
     * A string representing the name of the digest function to use. This can be one of SHA-256,
     * SHA-384, or SHA-512.
     */
    public var hash: String
}

/**
 * Represents the object that should be passed as the algorithm parameter into
 * SubtleCrypto.importKey() or SubtleCrypto.unwrapKey(), when generating any elliptic-curve-based
 * key pair: that is, when the algorithm is identified as either of ECDSA or ECDH.
 *
 * @see [EcKeyImportParams](https://developer.mozilla.org/en-US/docs/Web/API/EcKeyImportParams)
 */
public external interface EcKeyImportParams : KeyImportParams {

    /**
     * A string. This should be set to ECDSA or ECDH, depending on the algorithm you want to use.
     */
    public override var name: String

    /**
     * A string representing the name of the elliptic curve to use. This may be any of the
     * following names for NIST-approved curves:
     * - P-256
     * - P-384
     * - P-521
     */
    public var namedCurve: String
}

/**
 * epresents the object that should be passed as the algorithm parameter into
 * SubtleCrypto.importKey() or SubtleCrypto.unwrapKey(), when generating a key for the HMAC
 * algorithm.
 *
 * @see [HmacImportParams](https://developer.mozilla.org/en-US/docs/Web/API/HmacImportParams)
 */
public external interface HmacImportParams : KeyImportParams {

    /**
     * A string. This should be set to HMAC.
     */
    public override var name: String

    /**
     * A string representing the name of the digest function to use. The can take a value of
     * SHA-256, SHA-384, or SHA-512.
     */
    public var hash: String

    /**
     * A Number representing the length in bits of the key. If this is omitted the length of the
     * key is equal to the length of the digest generated by the digest function you have chosen.
     * Unless you have a good reason to use a different length, omit this property and use the
     * default.
     */
    public var length: Int?
}

public fun KeyImportParams(name: String): KeyImportParams =
    createKeyImportParams().apply {
        this.name = name
    }

public fun RsaHashedImportParams(
    name: String,
    hash: String
): RsaHashedImportParams =
    createRsaHashedImportParams().apply {
        this.name = name
        this.hash = hash
    }

public fun EcKeyImportParams(
    name: String,
    namedCurve: String
): EcKeyImportParams =
    createEcKeyImportParams().apply {
        this.name = name
        this.namedCurve = namedCurve
    }

public fun HmacImportParams(
    hash: String,
    length: Int? = null
): HmacImportParams =
    createHmacImportParams().apply {
        this.name = "HMAC"
        this.hash = hash

        if (length != null) {
            this.length = length
        }
    }

private fun createKeyImportParams(): KeyImportParams =
    createGenericObject().unsafeCast()

private fun createRsaHashedImportParams(): RsaHashedImportParams =
    createGenericObject().unsafeCast()

private fun createEcKeyImportParams(): EcKeyImportParams =
    createGenericObject().unsafeCast()

private fun createHmacImportParams(): HmacImportParams =
    createGenericObject().unsafeCast()

private fun createGenericObject(): JsAny =
    js("{}")
