package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.Jwk
import com.mooncloak.kodetools.kjwt.core.UnsupportedJwtSignatureAlgorithm
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

//@ExperimentalJwtApi
///@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
// FIXME: public expect suspend fun SignatureAlgorithm.generateSigningKey(json: Json = Json.Default): Jwk?
