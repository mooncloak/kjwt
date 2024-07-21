package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
public actual suspend fun SignatureAlgorithm.generateSigningKey(json: Json): Jwk? = TODO()
