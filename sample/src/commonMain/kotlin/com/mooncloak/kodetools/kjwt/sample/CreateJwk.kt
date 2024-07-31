package com.mooncloak.kodetools.kjwt.sample

import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.key.KeyOperation
import com.mooncloak.kodetools.kjwt.core.key.KeyType
import com.mooncloak.kodetools.kjwt.core.key.KeyUse
import com.mooncloak.kodetools.kjwt.core.key.invoke
import com.mooncloak.kodetools.kjwt.core.key.signatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

@OptIn(ExperimentalJwtApi::class)
public fun createJwk() {
    Jwk(keyType = KeyType.RSA) {
        keyId = "abc123"
        signatureAlgorithm = SignatureAlgorithm.HS256
        keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
        use = KeyUse.Sig
        // ...
    }
}
