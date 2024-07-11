package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import com.mooncloak.kodetools.kjwt.key.KeyMaterial
import kotlinx.serialization.json.Json

@ExperimentalJwtApi
@ExperimentalKeyApi
public expect fun Jwk.toKeyMaterial(json: Json): KeyMaterial

@ExperimentalJwtApi
@ExperimentalKeyApi
public expect fun KeyMaterial.toJwk(json: Json): Jwk

@ExperimentalJwtApi
@ExperimentalKeyApi
public expect fun SignatureAlgorithm.generateSigningKey(): KeyMaterial?

@ExperimentalKeyApi
@ExperimentalJwtApi
public expect fun SignatureAlgorithm.keyFrom(bytes: ByteArray): Key
