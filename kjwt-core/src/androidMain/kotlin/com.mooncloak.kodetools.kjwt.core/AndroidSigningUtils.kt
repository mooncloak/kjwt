package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
internal actual suspend fun sign(
    input: String,
    key: Jwk,
    algorithm: SignatureAlgorithm
): Signature = TODO()

@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
public actual suspend fun SignatureAlgorithm.generateSigningKey(json: Json): Jwk? = TODO()
