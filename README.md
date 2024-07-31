# kjwt

**kjwt** = **K**otlin **J**SON **W**eb **T**okens

Kotlin multi-platform JSON Web Token (JWT/JWS) library.

<img alt="GitHub tag (latest by date)" src="https://img.shields.io/github/v/tag/mooncloak/kjwt">

## Getting Started 🏁

Checkout the [releases page](https://github.com/mooncloak/kjwt/releases) to get the latest version.
<br/><br/>
<img alt="GitHub tag (latest by date)" src="https://img.shields.io/github/v/tag/mooncloak/kjwt">

### Repository

```kotlin
repositories {
    maven("https://repo.repsy.io/mvn/mooncloak/public")
}
```

### Dependencies

```kotlin
implementation("com.mooncloak.kodetools.kjwt:kjwt-core:VERSION")
```

## Usage

The following library usage examples, and more, can be found in the [sample module](sample).

### Creating a JWT (Signed JWS)

The following example illustrates how to create a [CompactedJwt](docs/). The full source code can be
found [here](sample/src/commonMain/kotlin/com/mooncloak/kodetools/kjwt/sample/CreateJwt.kt).

```kotlin
Jwt {
    header {
        signatureAlgorithm = algorithm
        keyId = "MY_KEY_ID"
        // ...
    }

    payload {
        this.issuedAt = Clock.System.now()
        this["custom_claim"] = JsonPrimitive("CUSTOM_CLAIM_VALUE")
        this.putValue(key = "other_custom_claim", value = 0)
        // ...
    }
}.sign(
    resolver = keyResolver,
    algorithm = algorithm
).compact()
```

### Parsing a compacted JWS

The following example illustrates how to parse a [CompactedJwt](docs/). The full source code can be
found [here](sample/src/commonMain/kotlin/com/mooncloak/kodetools/kjwt/sample/ParseCompactedJwt.kt).

```kotlin
val (header, payload, signature) = Jws.parse(
    compacted = compactedJwt,
    resolver = keyResolver
)
```

### JSON Web Keys (JWKs and JWKSets)

JWKs are used to sign, verify, encrypt, and decrypt JWTs. They are essential for working with JWTs
and it is up to an application to define where it obtains its JWKs. This library
provides [Jwk](docs/kjwt-core/kjwt-core/com.mooncloak.kodetools.kjwt.core.key/-jwk/index.md)
and [JwkSet](docs/kjwt-core/kjwt-core/com.mooncloak.kodetools.kjwt.core.key/-jwkset/index.md)
components, as well as
a [KeyResolver](docs/kjwt-core/kjwt-core/com.mooncloak.kodetools.kjwt.core.key/-key-resolver/index.md)
component which is used to obtain a JWK.

#### Creating a JWK

To create a JSON Web Key, use the [Jwk.invoke] constructor function:

```kotlin
Jwk(keyType = KeyType.RSA) {
    keyId = "abc123"
    signatureAlgorithm = SignatureAlgorithm.HS256
    keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
    use = KeyUse.Sig
    // ...
}
```

The full source code can be
found [here](sample/src/commonMain/kotlin/com/mooncloak/kodetools/kjwt/sample/CreateJwk.kt)

## Documentation 📃

More detailed documentation is available in the [docs](docs/) folder. The entry point to the
documentation can be
found [here](docs/index.md).

## Security 🛡️

For security vulnerabilities, concerns, or issues, please refer to
the [security policy](SECURITY.md) for more
information on appropriate approaches for disclosure.

## Contributing ✍️

Outside contributions are welcome for this project. Please follow
the [code of conduct](CODE_OF_CONDUCT.md)
and [coding conventions](CODING_CONVENTIONS.md) when contributing. If contributing code, please add
thorough documents
and tests. Thank you!

## License ⚖️

```
Copyright 2024 mooncloak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
