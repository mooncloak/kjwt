package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import com.mooncloak.kodetools.kjwt.key.KeyMaterial
import kotlinx.serialization.json.Json

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun Jwk.toKeyMaterial(json: Json): KeyMaterial = TODO()

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun KeyMaterial.toJwk(json: Json): Jwk = TODO()

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun SignatureAlgorithm.generateSigningKey(): KeyMaterial? = TODO()

@ExperimentalKeyApi
@ExperimentalJwtApi
public actual fun SignatureAlgorithm.keyFrom(bytes: ByteArray): Key = TODO()
