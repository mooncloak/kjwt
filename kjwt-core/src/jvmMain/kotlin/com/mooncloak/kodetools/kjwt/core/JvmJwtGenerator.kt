package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.json.Json

@ExperimentalJwtApi
internal actual val DefaultJwtGenerator: Jwt.Generator
    get() = JvmJwtGenerator

@ExperimentalJwtApi
internal data object JvmJwtGenerator : Jwt.Generator {

    override suspend fun generate(
        json: Json,
        builder: Jwt.Builder.() -> Unit
    ): Jwt = JvmJwtBuilder(json = json)
        .apply(builder)
        .build()
}
