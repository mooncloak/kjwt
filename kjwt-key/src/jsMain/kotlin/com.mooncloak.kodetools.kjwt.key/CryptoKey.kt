package com.mooncloak.kodetools.kjwt.key

public sealed external interface CryptoKeyResult

// https://developer.mozilla.org/en-US/docs/Web/API/CryptoKey
public external interface CryptoKey : CryptoKeyResult {

    /**
     * The type of key this instance represents. Can be "secret", "private", or "public".
     */
    public val type: String

    /**
     * Indicates whether or not the key may be extracted using the SubtleCrypto.exportKey or
     * SubtleCrypto.wrapKey functions.
     */
    public val extractable: Boolean

    /**
     * An object describing the algorithm for which this key can be used and any associated extra
     * parameters.
     */
    public val algorithm: KeyGenParams?

    /**
     * Indicates what can be done with the key. Possible values include "encrypt", "decrypt",
     * "sign", "verify", "deriveKey", "deriveBits", "wrapKey", and "unwrapKey".
     */
    public val usages: List<String>
}

// https://developer.mozilla.org/en-US/docs/Web/API/CryptoKeyPair
public external interface CryptoKeyPair : CryptoKeyResult {

    /**
     * A [CryptoKey] representing a private key.
     */
    public val privateKey: CryptoKey

    /**
     * A [CryptoKey] representing a public key.
     */
    public val publicKey: CryptoKey
}
