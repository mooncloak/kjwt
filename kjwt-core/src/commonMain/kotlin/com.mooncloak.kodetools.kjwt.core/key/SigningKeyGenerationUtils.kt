package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.json.Json

@ExperimentalJwtApi
internal expect suspend fun generateRsaSigningKey(
    algorithm: SignatureAlgorithm,
    bitCount: Int,
    json: Json
): Jwk?
