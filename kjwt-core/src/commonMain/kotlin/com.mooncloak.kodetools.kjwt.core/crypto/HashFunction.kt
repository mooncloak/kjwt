package com.mooncloak.kodetools.kjwt.core.crypto

import org.kotlincrypto.hash.md.MD5
import org.kotlincrypto.hash.sha1.SHA1
import org.kotlincrypto.hash.sha2.SHA224
import org.kotlincrypto.hash.sha2.SHA256
import org.kotlincrypto.hash.sha2.SHA384
import org.kotlincrypto.hash.sha2.SHA512
import org.kotlincrypto.hash.sha2.SHA512_224
import org.kotlincrypto.hash.sha2.SHA512_256
import org.kotlincrypto.hash.sha2.SHA512t

/**
 * A component that performs a function to produce hash bytes.
 */
public fun interface HashFunction {

    public fun hash(bytes: ByteArray): ByteArray

    public companion object
}

/**
 * Retrieves a [HashFunction] instances that uses the MD5 hash algorithm.
 */
public val HashFunction.Companion.MD5: HashFunction
    get() = MD5HashFunction

/**
 * Retrieves a [HashFunction] instances that uses the SHA-1 hash algorithm.
 */
public val HashFunction.Companion.Sha1: HashFunction
    get() = Sha1HashFunction

/**
 * Retrieves a [HashFunction] instances that uses the SHA-224 hash algorithm.
 */
public val HashFunction.Companion.Sha224: HashFunction
    get() = Sha224HashFunction

/**
 * Retrieves a [HashFunction] instances that uses the SHA-256 hash algorithm.
 */
public val HashFunction.Companion.Sha256: HashFunction
    get() = Sha256HashFunction

/**
 * Retrieves a [HashFunction] instances that uses the SHA-384 hash algorithm.
 */
public val HashFunction.Companion.Sha384: HashFunction
    get() = Sha384HashFunction

/**
 * Retrieves a [HashFunction] instances that uses the SHA-512 hash algorithm.
 */
public val HashFunction.Companion.Sha512: HashFunction
    get() = Sha512HashFunction

/**
 * Retrieves a [HashFunction] instances that uses the SHA-512/224 hash algorithm.
 */
public val HashFunction.Companion.Sha512_224: HashFunction
    get() = Sha512224HashFunction

/**
 * Retrieves a [HashFunction] instances that uses the SHA-512/256 hash algorithm.
 */
public val HashFunction.Companion.Sha512_256: HashFunction
    get() = Sha512256HashFunction

internal data object MD5HashFunction : HashFunction {

    private val delegate: MD5 = MD5()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}

internal data object Sha1HashFunction : HashFunction {

    private val delegate: SHA1 = SHA1()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}

internal data object Sha224HashFunction : HashFunction {

    private val delegate: SHA224 = SHA224()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}

internal data object Sha256HashFunction : HashFunction {

    private val delegate: SHA256 = SHA256()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}

internal data object Sha384HashFunction : HashFunction {

    private val delegate: SHA384 = SHA384()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}

internal data object Sha512HashFunction : HashFunction {

    private val delegate: SHA512 = SHA512()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}

internal data object Sha512224HashFunction : HashFunction {

    private val delegate: SHA512t = SHA512_224()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}

internal data object Sha512256HashFunction : HashFunction {

    private val delegate: SHA512t = SHA512_256()

    override fun hash(bytes: ByteArray): ByteArray =
        delegate.digest(bytes)
}
