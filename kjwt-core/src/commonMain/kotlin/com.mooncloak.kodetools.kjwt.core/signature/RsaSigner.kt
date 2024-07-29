package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.key.Jwk

@ExperimentalJwtApi
internal data object RsaSigner: Signer {

    internal val supportedAlgorithms = setOf(
        SignatureAlgorithm.RS256,
        SignatureAlgorithm.RS384,
        SignatureAlgorithm.RS512
    )

    override suspend fun sign(
        input: SignatureInput,
        key: Jwk,
        algorithm: SignatureAlgorithm
    ): Signature {
        TODO("Not yet implemented")
    }
}
