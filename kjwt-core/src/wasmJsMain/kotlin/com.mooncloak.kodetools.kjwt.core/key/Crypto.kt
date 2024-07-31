package com.mooncloak.kodetools.kjwt.core.key

import org.w3c.dom.Window
import kotlin.js.Promise

internal external interface Crypto : JsAny {

    val subtle: SubtleCrypto
}

internal external interface SubtleCrypto : JsAny {

    // Note: Only implemented for RSA scenario
    fun generateKey(
        algorithm: RsaHashedKeyGenParams,
        extractable: Boolean,
        keyUsages: JsArray<JsString>
    ): Promise<CryptoKeyPair>

    // Note: Only implemented for JWK scenario
    fun exportKey(
        format: String,
        key: CryptoKeyPair
    ): Promise<Json>
}

@Suppress("UnusedReceiverParameter")
internal val Window.crypto: Crypto
    get() = getCryptoInstance()

private fun getCryptoInstance(): Crypto = getJsCryptoAsAny().unsafeCast()

private fun getJsCryptoAsAny(): JsAny = js("window.crypto")
