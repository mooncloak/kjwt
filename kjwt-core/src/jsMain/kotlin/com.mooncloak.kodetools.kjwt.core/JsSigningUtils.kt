package com.mooncloak.kodetools.kjwt.core

@ExperimentalJwtApi
internal actual suspend fun sign(
    input: String,
    key: Jwk,
    algorithm: SignatureAlgorithm
): Signature = TODO()

@ExperimentalJwtApi
public actual suspend fun SignatureAlgorithm.generateSigningKey(): Jwk? = TODO()
