package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
public expect suspend fun SignatureAlgorithm.generateSigningKey(json: Json = Json.Default): Jwk?
