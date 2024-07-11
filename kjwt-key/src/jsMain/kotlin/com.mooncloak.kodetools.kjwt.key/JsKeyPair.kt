package com.mooncloak.kodetools.kjwt.key

@ExperimentalKeyApi
public actual class KeyPair : KeyMaterial {

    public actual val publicKey: PublicKey
    public actual val privateKey: PrivateKey

    @Suppress("MemberVisibilityCanBePrivate")
    public val native: CryptoKeyPair?

    public actual constructor(
        publicKey: PublicKey,
        privateKey: PrivateKey
    ) {
        this.publicKey = publicKey
        this.privateKey = privateKey
        this.native = null
    }

    public constructor(
        publicKey: CryptoKey,
        privateKey: CryptoKey
    ) {
        this.publicKey =
            com.mooncloak.kodetools.kjwt.key.PublicKey(native = publicKey)
        this.privateKey =
            com.mooncloak.kodetools.kjwt.key.PrivateKey(native = privateKey)
        this.native = null
    }

    public constructor(
        native: CryptoKeyPair
    ) {
        this.publicKey =
            com.mooncloak.kodetools.kjwt.key.PublicKey(native = native.publicKey)
        this.privateKey =
            com.mooncloak.kodetools.kjwt.key.PrivateKey(native = native.privateKey)
        this.native = native
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KeyPair) return false

        if (publicKey != other.publicKey) return false
        if (privateKey != other.privateKey) return false

        return native == other.native
    }

    override fun hashCode(): Int {
        var result = publicKey.hashCode()
        result = 31 * result + privateKey.hashCode()
        result = 31 * result + (native?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "KeyPair(publicKey=$publicKey, privateKey=$privateKey, native=$native)"

    public actual companion object
}

@ExperimentalKeyApi
public fun CryptoKeyPair.toKeyPair(): KeyPair =
    com.mooncloak.kodetools.kjwt.key.KeyPair(native = this)
