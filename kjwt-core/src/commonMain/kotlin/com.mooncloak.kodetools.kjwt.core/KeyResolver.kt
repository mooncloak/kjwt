package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.key.ExperimentalKeyApi
import com.mooncloak.kodetools.kjwt.key.Key

/**
 * A component that resolves the [Key] used when signing a [Jws]. This could be used to dynamically
 * resolve the signing key for verification or signing purposes.
 */
@ExperimentalJwtApi
public fun interface KeyResolver {

    /**
     * Returns the signing key that should be used to validate a digital signature for the
     * Claims JWS with the specified header and claims.
     *
     * @param header the header of the JWS to validate
     *
     * @return the key that should be used to validate a digital signature for the Claims JWS with
     * the specified header and claims.
     */
    @ExperimentalKeyApi
    public suspend fun resolve(
        header: Header
    ): Key

    public companion object
}
