package com.mooncloak.kodetools.kjwt.core

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
public open class UnsupportedJwtSignatureAlgorithm public constructor(
    message: String? = null,
    cause: Throwable? = null
) : JwtException(
    message = message,
    cause = cause
)
