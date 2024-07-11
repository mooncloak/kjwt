package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key
import kotlinx.serialization.json.Json

/**
 * A component that can be signed into a [Jws].
 */
@ExperimentalJwtApi
public fun interface Signable {

    /**
     * Signs this [Jwt] using the provided [algorithm] and [key] to create a [Jws].
     */
    @ExperimentalKeyApi
    public suspend fun sign(
        key: Key,
        algorithm: SignatureAlgorithm?,
        json: Json
    ): Jws
}
