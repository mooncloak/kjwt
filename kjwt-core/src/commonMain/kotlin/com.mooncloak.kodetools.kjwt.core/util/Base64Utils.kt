package com.mooncloak.kodetools.kjwt.core.util

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Encodes this [ByteArray] using Base64 URL-safe encoding without the trailing '=' padding
 * character.
 *
 * > [!Note]
 * > The Kotlin [Base64.UrlSafe] component always encodes with the padding character '='. Because
 * > it does not provide a way to opt-out of using padding, we have to explicitly remove the
 * > trailing padding characters here. Since this is a common operation, this utility function was
 * > created to handle the operation.
 */
@OptIn(ExperimentalEncodingApi::class)
internal fun ByteArray.encodeBase64UrlSafeWithoutPadding(): String =
    Base64.UrlSafe.encode(source = this).trimEnd('=')
