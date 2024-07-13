package com.mooncloak.kodetools.kjwt.core

import org.kotlincrypto.hash.sha2.SHA256

/**
 * A component that performs a function to produce hash bytes.
 */
public fun interface HashFunction {

    public fun hash(bytes: ByteArray): ByteArray

    public companion object
}

/**
 * Retrieves a [HashFunction] instances that uses the SHA-256 hash algorithm.
 */
public val HashFunction.Companion.Sha256: HashFunction
    get() = Sha256HashFunction

internal data object Sha256HashFunction : HashFunction {

    private val delegate: SHA256 = SHA256()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}
