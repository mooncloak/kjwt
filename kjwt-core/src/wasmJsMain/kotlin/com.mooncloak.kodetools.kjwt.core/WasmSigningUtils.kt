package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key

@ExperimentalKeyApi
@ExperimentalJwtApi
internal actual suspend fun sign(
    input: String,
    key: Key,
    algorithm: SignatureAlgorithm
): Signature = TODO()
