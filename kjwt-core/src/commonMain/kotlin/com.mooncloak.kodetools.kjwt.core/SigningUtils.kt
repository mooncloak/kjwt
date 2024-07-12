package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import kotlin.coroutines.cancellation.CancellationException

@ExperimentalKeyApi
@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
internal expect suspend fun sign(
    input: String,
    key: Key,
    algorithm: SignatureAlgorithm
): Signature
