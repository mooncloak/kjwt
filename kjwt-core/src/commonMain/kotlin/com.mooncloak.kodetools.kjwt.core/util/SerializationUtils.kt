package com.mooncloak.kodetools.kjwt.core.util

import com.mooncloak.kodetools.kjwt.core.JwtObject
import com.mooncloak.kodetools.kjwt.core.toJsonObject
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

/**
 * Attempts to convert this value to a [JsonElement] returning the result, or throws an
 * [IllegalStateException] if this value cannot be converted to a [JsonElement].
 *
 * This function is used internally because some implementations of the JWT utils may store the
 * properties of a [JwtObject] as a generic map with values of type [Any]. Though the
 * implementation should guard against invalid objects being stored, it is not always possible to
 * do so, therefore we make the best case effort to convert the value to a [JsonElement] or throw
 * an exception if we can't.
 *
 * > [!Note]
 * > This doesn't use the Json object or SerializersModule because this function will most likely
 * > be invoked from a position where the type values are unknown or have been removed via type
 * > erasure.
 *
 * > [!Warning]
 * > This is an internal function and should not be exposed outside the use of this library.
 */
@ExperimentalJwtApi
@OptIn(ExperimentalSerializationApi::class)
internal fun Any?.toJsonElement(): JsonElement =
    when (this) {
        null -> JsonNull

        is JsonElement -> this

        is JwtObject -> this.toJsonObject()

        is Boolean -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is UByte -> JsonPrimitive(this)
        is UShort -> JsonPrimitive(this)
        is UInt -> JsonPrimitive(this)
        is ULong -> JsonPrimitive(this)
        is Char -> JsonPrimitive(this.toString())
        is String -> JsonPrimitive(this)

        is Collection<*> -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is Array<*> -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is BooleanArray -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is ByteArray -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is ShortArray -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is IntArray -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is LongArray -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is FloatArray -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is DoubleArray -> buildJsonArray {
            this@toJsonElement.forEach { value ->
                this.add(value.toJsonElement())
            }
        }

        is Map<*, *> -> buildJsonObject {
            this@toJsonElement.entries.forEach { (key, value) ->
                this.put(key.toString(), value.toJsonElement())
            }
        }

        else -> error("Invalid type cannot be converted to a JsonElement: $this")
    }
