package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents the signature input for a [Signer].
 *
 * @see [Signer]
 */
@JvmInline
@Serializable
@ExperimentalJwtApi
public value class SignatureInput public constructor(
    public val value: String
) {

    public companion object
}
