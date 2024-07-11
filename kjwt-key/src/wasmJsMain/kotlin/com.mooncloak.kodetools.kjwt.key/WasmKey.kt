package com.mooncloak.kodetools.kjwt.key

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.khronos.webgl.ArrayBuffer

@ExperimentalKeyApi
public actual abstract class Key internal constructor(
    public val native: CryptoKey
) : KeyMaterial {

    public actual companion object
}

@ExperimentalKeyApi
public actual class PublicKey public constructor(
    native: CryptoKey
) : Key(native = native) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PublicKey) return false

        return native == other.native
    }

    override fun hashCode(): Int =
        native.hashCode()

    override fun toString(): String =
        "PublicKey(native=$native)"
}

@ExperimentalKeyApi
public actual class PrivateKey public constructor(
    native: CryptoKey
) : Key(native = native) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PrivateKey) return false

        return native == other.native
    }

    override fun hashCode(): Int =
        native.hashCode()

    override fun toString(): String =
        "PrivateKey(native=$native)"
}

@ExperimentalKeyApi
public class GenericKey internal constructor(
    native: CryptoKey
) : Key(native = native) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GenericKey) return false

        return native == other.native
    }

    override fun hashCode(): Int =
        native.hashCode()

    override fun toString(): String =
        "GenericKey(native=$native)"
}

@ExperimentalKeyApi
public actual suspend fun Key.encoded(): ByteArray? = if (this.native.extractable) {
    val result = window.crypto.subtle.exportKey("raw", this.native).await<JsAny>()

    if (result is ArrayBuffer) {
        result.toByteArray()
    } else {
        null
    }
} else {
    null
}

@ExperimentalKeyApi
public operator fun Key.Companion.invoke(
    native: CryptoKey
): Key = GenericKey(native = native)

@ExperimentalKeyApi
public fun CryptoKey.toKey(): Key =
    when (this.type) {
        "private" -> com.mooncloak.kodetools.kjwt.key.PrivateKey(native = this)
        "public" -> com.mooncloak.kodetools.kjwt.key.PublicKey(native = this)
        else -> GenericKey(native = this)
    }
