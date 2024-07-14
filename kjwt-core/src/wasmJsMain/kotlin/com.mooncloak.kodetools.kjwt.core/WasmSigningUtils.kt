package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.json.Json

@ExperimentalJwtApi
internal actual suspend fun sign(
    input: String,
    key: Jwk,
    algorithm: SignatureAlgorithm
): Signature = TODO()

@ExperimentalJwtApi
public actual suspend fun SignatureAlgorithm.generateSigningKey(json: Json): Jwk? = TODO()
