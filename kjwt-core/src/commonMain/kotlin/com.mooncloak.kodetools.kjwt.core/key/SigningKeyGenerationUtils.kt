package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.crypto.RsaKeyPair

internal expect suspend fun generateRsaKeyPair(bitCount: Int): RsaKeyPair?
