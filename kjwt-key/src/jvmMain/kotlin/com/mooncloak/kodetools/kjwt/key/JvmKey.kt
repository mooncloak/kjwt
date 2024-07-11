@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.mooncloak.kodetools.kjwt.key

@ExperimentalKeyApi
public actual abstract class Key internal constructor() : KeyMaterial {

    public abstract val native: java.security.Key

    public actual companion object
}

@ExperimentalKeyApi
public actual class PublicKey public constructor(
    public override val native: java.security.PublicKey
) : Key() {

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
    public override val native: java.security.PrivateKey
) : Key() {

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
    public override val native: java.security.Key
) : Key() {

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
public actual suspend fun Key.encoded(): ByteArray? = this.native.encoded

@ExperimentalKeyApi
public operator fun Key.Companion.invoke(
    native: java.security.Key
): Key = GenericKey(native = native)

@ExperimentalKeyApi
public fun java.security.PublicKey.toKey(): PublicKey =
    com.mooncloak.kodetools.kjwt.key.PublicKey(native = this)

@ExperimentalKeyApi
public fun java.security.PrivateKey.toKey(): PrivateKey =
    com.mooncloak.kodetools.kjwt.key.PrivateKey(native = this)

@ExperimentalKeyApi
public fun java.security.Key.toKey(): Key =
    when (this) {
        is java.security.PublicKey -> com.mooncloak.kodetools.kjwt.key.PublicKey(
            native = this
        )

        is java.security.PrivateKey -> com.mooncloak.kodetools.kjwt.key.PrivateKey(
            native = this
        )

        else -> GenericKey(native = this)
    }
