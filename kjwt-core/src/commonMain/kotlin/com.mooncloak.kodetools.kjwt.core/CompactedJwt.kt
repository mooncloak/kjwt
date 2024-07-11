package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
@ExperimentalJwtApi
public value class CompactedJwt public constructor(
    public val value: String
)
