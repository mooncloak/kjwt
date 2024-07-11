package com.mooncloak.kodetools.kjwt.key

import org.w3c.dom.Window
import kotlin.js.Promise

public external interface Crypto {

    public val subtle: SubtleCrypto
}

public external interface SubtleCrypto {

    public fun exportKey(format: String, key: CryptoKey): Promise<Any>
}

@Suppress("UnusedReceiverParameter")
public val Window.crypto: Crypto
    get() = getCryptoInstance()

private fun getCryptoInstance(): Crypto = js("window.crypto").unsafeCast<Crypto>()
