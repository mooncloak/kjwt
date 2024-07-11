package com.mooncloak.kodetools.kjwt.key

@ExperimentalKeyApi
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
public actual class KeyPair public constructor(
    public val native: java.security.KeyPair
) : KeyMaterial {

    public actual constructor(
        publicKey: PublicKey,
        privateKey: PrivateKey
    ) : this(
        native = java.security.KeyPair(
            publicKey.native,
            privateKey.native
        )
    )

    public actual val privateKey: PrivateKey
        inline get() = native.private.toKey()

    public actual val publicKey: PublicKey
        inline get() = native.public.toKey()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KeyPair) return false

        return native == other.native
    }

    override fun hashCode(): Int =
        native.hashCode()

    override fun toString(): String =
        "KeyPair(native=$native)"

    public actual companion object
}

@ExperimentalKeyApi
public fun java.security.KeyPair.toKeyPair(): KeyPair =
    com.mooncloak.kodetools.kjwt.key.KeyPair(native = this)
