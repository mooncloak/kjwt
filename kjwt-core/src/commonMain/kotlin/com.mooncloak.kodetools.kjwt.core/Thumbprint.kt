package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.crypto.HashFunction
import com.mooncloak.kodetools.kjwt.core.crypto.Sha256
import com.mooncloak.kodetools.kjwt.core.key.Jwk
import com.mooncloak.kodetools.kjwt.core.key.KeyType
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.jvm.JvmInline

/**
 * Represents a [Jwk] thumbprint value defined by the
 * [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638). This class provides type
 * safety for a [String] value representing a [Jwk] thumbprint, but is still performant since it is
 * a value class.
 *
 * @property [value] The actual [Jwk] thumbprint [String] value.
 *
 * @see [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638)
 */
@JvmInline
@Serializable
public value class Thumbprint public constructor(
    public val value: String
)

/**
 * Creates a JSON Web Key Thumbprint according to the
 * [specification](https://www.rfc-editor.org/rfc/rfc7638).
 *
 * @param [hashFunction] The [HashFunction] used in the thumbprint calculation. Defaults to
 * [HashFunction.Companion.Sha256].
 *
 * @see [JWK Thumbprint Specification](https://www.rfc-editor.org/rfc/rfc7638)
 */
@OptIn(ExperimentalEncodingApi::class)
@ExperimentalJwtApi
public fun Jwk.thumbprint(
    hashFunction: HashFunction = HashFunction.Sha256
): Thumbprint {
    // Create a JSON Object containing only the required properties for a particular algorithm
    // (note that order matters):
    // https://www.rfc-editor.org/rfc/rfc7638#section-3.2

    // Note that the JSON Object implementation seems to use a LinkedHashMap which preserves entry
    // order. However, this could change at any time breaking this implementation.
    // TODO: Guarantee JSON Object entry order.
    val jsonObject = buildJsonObject {
        when (keyType) {
            KeyType.EC -> {
                put(Jwk.PropertyKey.CURVE, curve)
                put(Jwk.PropertyKey.KEY_TYPE, keyType.value)
                put(Jwk.PropertyKey.X, x)
                put(Jwk.PropertyKey.Y, y)
            }

            KeyType.RSA -> {
                put(Jwk.PropertyKey.E, e)
                put(Jwk.PropertyKey.KEY_TYPE, keyType.value)
                put(Jwk.PropertyKey.N, n)
            }

            KeyType.OCT -> {
                put(Jwk.PropertyKey.K, k)
                put(Jwk.PropertyKey.KEY_TYPE, keyType.value)
            }
        }
    }

    // Creates a copy of our current Json instance that asserts that the encoding format will be
    // correct. For example, we must not use pretty printing here, but the default Json instance
    // may be set to use it, so we explicitly make a copy and set that value to `false`. Making a
    // copy is important because it allows us to still use the Serializers Module from the Json
    // instance.
    val json = Json(from = this.json) {
        isLenient = false
        prettyPrint = false
    }

    val jsonString = json.encodeToString(
        serializer = JsonObject.serializer(),
        value = jsonObject
    )
    val utfEncodedBytes = jsonString.encodeToByteArray()
    val hashedBytes = hashFunction.hash(utfEncodedBytes)

    return Thumbprint(value = Base64.UrlSafe.encode(hashedBytes))
}
