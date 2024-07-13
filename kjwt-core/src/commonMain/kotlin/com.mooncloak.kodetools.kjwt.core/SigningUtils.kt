package com.mooncloak.kodetools.kjwt.core

import kotlin.coroutines.cancellation.CancellationException

@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
internal expect suspend fun sign(
    input: String,
    key: Jwk,
    algorithm: SignatureAlgorithm
): Signature

@ExperimentalJwtApi
@Throws(UnsupportedJwtSignatureAlgorithm::class, CancellationException::class)
public expect suspend fun SignatureAlgorithm.generateSigningKey(): Jwk?
