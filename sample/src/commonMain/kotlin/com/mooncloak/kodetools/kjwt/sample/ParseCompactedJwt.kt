package com.mooncloak.kodetools.kjwt.sample

import com.mooncloak.kodetools.kjwt.core.CompactedJwt
import com.mooncloak.kodetools.kjwt.core.Jws
import com.mooncloak.kodetools.kjwt.core.component1
import com.mooncloak.kodetools.kjwt.core.component2
import com.mooncloak.kodetools.kjwt.core.component3
import com.mooncloak.kodetools.kjwt.core.key.KeyResolver
import com.mooncloak.kodetools.kjwt.core.parse
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

@OptIn(ExperimentalJwtApi::class)
public suspend fun parseCompactedJwt(
    compacted: CompactedJwt,
    keyResolver: KeyResolver
) {
    val (header, payload, signature) = Jws.parse(
        compacted = compacted,
        resolver = keyResolver
    )
}
