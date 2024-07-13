package com.mooncloak.kodetools.kjwt.key

import org.w3c.dom.Window
import kotlin.js.Promise

public external interface Crypto : JsAny {

    public val subtle: SubtleCrypto
}

public external interface SubtleCrypto : JsAny {

    public fun exportKey(format: String, key: CryptoKey): Promise<JsAny>

    /**
     * Imports a key: that is, it takes as input a key in an external, portable format and gives
     * you a CryptoKey object that you can use in the Web Crypto API.
     *
     * @param [format] A string describing the data format of the key to import. It can be one of
     * the following:
     * - raw: [Raw](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/importKey#raw) format.
     * - pkcs8: [PKCS #8](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/importKey#pkcs_8) format.
     * - spki: [SubjectPublicKeyInfo](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/importKey#subjectpublickeyinfo) format.
     * - jwk: [JSON Web Key](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/importKey#json_web_key) format.
     *
     * @param [keyData] An ArrayBuffer, a TypedArray, a DataView, or a JSONWebKey object containing
     * the key in the given format.
     *
     * @param [algorithm]
     *
     * @param [extractable] A boolean value indicating whether it will be possible to export the
     * key using SubtleCrypto.exportKey() or SubtleCrypto.wrapKey().
     *
     * @param [keyUsages] An Array indicating what can be done with the key.
     *
     * @see (https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto/importKey)
     */
    public fun importKey(
        format: String,
        keyData: JsAny,
        algorithm: String,
        extractable: Boolean,
        keyUsages: JsArray<JsString>
    ): Promise<CryptoKey>
}

@Suppress("UnusedReceiverParameter")
public val Window.crypto: Crypto
    get() = getCryptoInstance().unsafeCast()

private fun getCryptoInstance(): JsAny = js("window.crypto")
