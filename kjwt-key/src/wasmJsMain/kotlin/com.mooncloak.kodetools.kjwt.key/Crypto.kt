package com.mooncloak.kodetools.kjwt.key

import org.w3c.dom.Window
import kotlin.js.Promise

public external interface Crypto : JsAny {

    public val subtle: SubtleCrypto
}

public external interface SubtleCrypto : JsAny {

    public fun exportKey(format: String, key: CryptoKey): Promise<JsAny>
}

@Suppress("UnusedReceiverParameter")
public val Window.crypto: Crypto
    get() = getCryptoInstance().unsafeCast()

private fun getCryptoInstance(): JsAny = js("window.crypto")
