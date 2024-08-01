package com.mooncloak.kodetools.kjwt.core.signature

import com.mooncloak.kodetools.kjwt.core.Claims
import com.mooncloak.kodetools.kjwt.core.Header
import com.mooncloak.kodetools.kjwt.core.JsonClaims
import com.mooncloak.kodetools.kjwt.core.Jwt
import com.mooncloak.kodetools.kjwt.core.invoke
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.key.KeyGenerator
import com.mooncloak.kodetools.kjwt.core.key.KeyOperation
import com.mooncloak.kodetools.kjwt.core.key.KeyResolver
import com.mooncloak.kodetools.kjwt.core.key.KeyType
import com.mooncloak.kodetools.kjwt.core.key.KeyUse
import com.mooncloak.kodetools.kjwt.core.key.invoke
import com.mooncloak.kodetools.kjwt.core.key.of
import com.mooncloak.kodetools.kjwt.core.key.signatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.key.signingKey
import com.mooncloak.kodetools.kjwt.core.property
import com.mooncloak.kodetools.kjwt.core.signatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import com.mooncloak.kodetools.kjwt.core.util.encodeBase64UrlSafeWithoutPadding
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

@OptIn(ExperimentalJwtApi::class)
class HmacSignerTest {

    @Test
    fun non_hmac_algorithm_throws_exception() {
        runTest {
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.RS256
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.RS384
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.RS512
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.PS256
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.PS384
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.PS512
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.ES256
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.ES384
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.ES512
                )
            }
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.NONE
                )
            }
        }
    }

    @Test
    fun missing_key_value_k_throws_an_exception() {
        runTest {
            assertFails {
                HmacSigner.sign(
                    input = SignatureInput(value = ""),
                    key = Jwk(keyType = KeyType.OCT),
                    algorithm = SignatureAlgorithm.HS256
                )
            }
        }
    }

    @Test
    fun test() {
        runTest {
            val generatedKey = KeyGenerator.signingKey(SignatureAlgorithm.HS256).generate()

            println("k = ${generatedKey?.k}")

            val jwt = Jwt {
                header {
                    signatureAlgorithm = SignatureAlgorithm.HS256
                    type = "JWT"
                }
                payload {
                    subject = "1234567890"
                    putValue(key = "name", value = "John Doe")
                    putValue(key = "iat", value = 1516239022)
                }
            }

            println("header: ${Json.encodeToString(Header.serializer(), jwt.header)}")
            println(
                "payload: ${
                    Json.encodeToString(
                        Claims.serializer(),
                        jwt.payload as JsonClaims
                    )
                }"
            )

            val signedJwt = jwt.sign(
                resolver = KeyResolver.of(hmac256Key)
            ).compact().value

            val signature = HmacSigner.sign(
                input = SignatureInput(value = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ"),
                algorithm = SignatureAlgorithm.HS256,
                key = hmac256Key
            )
            val decodedSignature = signature.value.encodeBase64UrlSafeWithoutPadding()

            println("Signature = $decodedSignature")

            assertEquals(
                expected = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.iAYfZKLi-FxPi5YeFrzUxGpQYMOHJNCI1dzgfWStysY",
                actual = signedJwt
            )
        }
    }

    private val hmac256Key: Jwk = Jwk(KeyType.OCT) {
        keyId = "49eedea7-d32c-43b3-8adc-f80c84b3f088"
        use = KeyUse.Sig
        keyOperations = listOf(KeyOperation.Sign, KeyOperation.Verify)
        signatureAlgorithm = SignatureAlgorithm.HS256
        k = "N5Vjzt_nrSi1X0jDHDugRqzB5Ac9LJbeDKN7RSbKneg"
    }
}
