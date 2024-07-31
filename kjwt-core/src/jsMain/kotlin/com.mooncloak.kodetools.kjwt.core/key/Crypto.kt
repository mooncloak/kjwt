package com.mooncloak.kodetools.kjwt.core.key

import org.w3c.dom.Window
import kotlin.js.Json
import kotlin.js.Promise

internal external interface Crypto {

    val subtle: SubtleCrypto
}

internal external interface SubtleCrypto {

    // Note: Only implemented for RSA scenario
    fun generateKey(
        algorithm: RsaHashedKeyGenParams,
        extractable: Boolean,
        keyUsages: List<String>
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

private fun getCryptoInstance(): Crypto = js("window.crypto").unsafeCast<Crypto>()
