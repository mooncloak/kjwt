@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.mooncloak.kodetools.kjwt.key

/**
 * Represents a cryptographic key used for signing or encrypting JWTs.
 */
@ExperimentalKeyApi
public expect abstract class Key : KeyMaterial {

    public companion object
}

@ExperimentalKeyApi
public expect class PublicKey : Key

@ExperimentalKeyApi
public expect class PrivateKey : Key

/**
 * Retrieves the key in its primary encoding format, or `null` if the key does not support
 * encoding.
 */
@ExperimentalKeyApi
public expect suspend fun Key.encoded(): ByteArray?
