package com.mooncloak.kodetools.kjwt.key

/**
 * This class is a simple holder for a key pair (a public key and a private key). It does not
 * enforce any security, and, when initialized, should be treated like a PrivateKey.
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@ExperimentalKeyApi
public expect class KeyPair public constructor(
    publicKey: PublicKey,
    privateKey: PrivateKey
) : KeyMaterial {

    /**
     * Returns a reference to the private key component of this key pair.
     */
    public val privateKey: PrivateKey

    /**
     * Returns a reference to the public key component of this key pair.
     */
    public val publicKey: PublicKey

    public companion object
}
