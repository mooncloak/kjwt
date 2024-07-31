//[kjwt-core](../../../index.md)/[com.mooncloak.kodetools.kjwt.core](../index.md)/[JsonClaims](index.md)/[notBefore](not-before.md)

# notBefore

[common]\
val [notBefore](not-before.md): Instant?

The &quot;nbf&quot; (not before) claim identifies the time before which the JWT MUST NOT be accepted for processing. This value should be a &quot;Numeric Date&quot; which is a JSON number value representing the **seconds** since the Epoch.

Value defaults to `null`.

#### See also

| | |
|---|---|
|  | [Numerical Date](https://www.rfc-editor.org/rfc/rfc7519#section-2) |
