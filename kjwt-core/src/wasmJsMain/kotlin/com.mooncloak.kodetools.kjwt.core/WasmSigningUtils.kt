package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.json.Json

@ExperimentalJwtApi
public actual suspend fun SignatureAlgorithm.generateSigningKey(json: Json): Jwk? = TODO()
