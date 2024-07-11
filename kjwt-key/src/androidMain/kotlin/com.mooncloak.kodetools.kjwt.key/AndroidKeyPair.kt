package com.mooncloak.kodetools.kjwt.key

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@ExperimentalKeyApi
public actual class KeyPair : KeyMaterial {

    public val native: java.security.KeyPair

    public actual constructor(
        publicKey: PublicKey,
        privateKey: PrivateKey
    ) : this(
        native = java.security.KeyPair(
            publicKey.native,
            privateKey.native
        )
    )

    public constructor(
        native: java.security.KeyPair
    ) {
        this.native = native
    }

    public actual val privateKey: PrivateKey
        get() = TODO("Not yet implemented")

    public actual val publicKey: PublicKey
        get() = TODO("Not yet implemented")

    public actual companion object
}
