package com.mooncloak.kodetools.kjwt.core.key

internal sealed external interface CryptoKeyMaterial : JsAny

// https://developer.mozilla.org/en-US/docs/Web/API/CryptoKey
internal external interface CryptoKey : CryptoKeyMaterial {

    /**
     * The type of key this instance represents. Can be "secret", "private", or "public".
     */
    val type: String

    /**
     * Indicates whether or not the key may be extracted using the SubtleCrypto.exportKey or
     * SubtleCrypto.wrapKey functions.
     */
    val extractable: Boolean

    /**
     * An object describing the algorithm for which this key can be used and any associated extra
     * parameters.
     */
    val algorithm: KeyGenParams?

    /**
     * Indicates what can be done with the key. Possible values include "encrypt", "decrypt",
     * "sign", "verify", "deriveKey", "deriveBits", "wrapKey", and "unwrapKey".
     */
    val usages: JsArray<JsString>
}

// https://developer.mozilla.org/en-US/docs/Web/API/CryptoKeyPair
internal external interface CryptoKeyPair : CryptoKeyMaterial {

    /**
     * A [CryptoKey] representing a private key.
     */
    val privateKey: CryptoKey

    /**
     * A [CryptoKey] representing a public key.
     */
    val publicKey: CryptoKey
}
