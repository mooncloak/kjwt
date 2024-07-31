//[kjwt-core](../../index.md)/[com.mooncloak.kodetools.kjwt.core](index.md)/[isUnsecured](is-unsecured.md)

# isUnsecured

[common]\
val [Jws](-jws/index.md).[isUnsecured](is-unsecured.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Determines whether this [Jws](-jws/index.md) is considered an &quot;unsecured&quot; JWT, which, according to the [JWT Specification](https://datatracker.ietf.org/doc/html/rfc7519#section-6), is a &quot;JWS using the &quot;alg&quot; Header Parameter value &quot;none&quot; and with the empty string for its JWS Signature value, as defined in the JWA specification.&quot;

#### See also

| | |
|---|---|
| [isSecured](is-secured.md) | for the inverse operation. |
|  | [JWA Specification](https://www.rfc-editor.org/rfc/rfc7518.html) |
| [Jws](-jws/index.md) |
