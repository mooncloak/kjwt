package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import kotlinx.serialization.json.Json

@ExperimentalJwtApi
public actual suspend fun SignatureAlgorithm.generateSigningKey(json: Json): Jwk? = TODO()
