//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[JsonClaims](index.md)/[expiration](expiration.md)

# expiration

[common]\
val [expiration](expiration.md): Instant?

The &quot;exp&quot; (expiration time) claim identifies the expiration time on or after which the JWT MUST NOT be accepted for processing. This value should be a &quot;Numeric Date&quot; which is a JSON number value representing the **seconds** since the Epoch.

Value defaults to `null`.

#### See also

| | |
|---|---|
|  | [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2) |
