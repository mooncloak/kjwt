package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi

@ExperimentalJwtApi
public open class JwtException public constructor(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)

@ExperimentalJwtApi
public open class JwtParseException public constructor(
    message: String? = null,
    cause: Throwable? = null
) : JwtException(
    message = message,
    cause = cause
)

@ExperimentalJwtApi
public open class JwtValidationException public constructor(
    message: String? = null,
    cause: Throwable? = null
) : JwtException(
    message = message,
    cause = cause
)

@ExperimentalJwtApi
public open class JwtSignatureException public constructor(
    message: String? = null,
    cause: Throwable? = null
) : JwtException(
    message = message,
    cause = cause
)

@ExperimentalJwtApi
public open class UnsupportedJwtSignatureAlgorithm public constructor(
    message: String? = null,
    cause: Throwable? = null
) : JwtSignatureException(
    message = message,
    cause = cause
)

@ExperimentalJwtApi
public open class InvalidSignatureKeyAlgorithm public constructor(
    message: String? = null,
    cause: Throwable? = null
) : JwtSignatureException(
    message = message,
    cause = cause
)
