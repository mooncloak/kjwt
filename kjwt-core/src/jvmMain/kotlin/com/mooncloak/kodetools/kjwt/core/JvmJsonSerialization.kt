package com.mooncloak.kodetools.kjwt.core

import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.JwtParserBuilder
import io.jsonwebtoken.io.Deserializer
import io.jsonwebtoken.io.Serializer
import io.jsonwebtoken.security.JwkParserBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import java.io.OutputStream
import java.io.Reader

@ExperimentalJwtApi
internal fun JwtBuilder.json(json: Json): JwtBuilder =
    this.json(JvmJsonSerializer(json = json))

@ExperimentalJwtApi
internal fun JwtParserBuilder.json(json: Json): JwtParserBuilder =
    this.json(JvmJsonSerializer(json = json))

@ExperimentalJwtApi
internal fun JwkParserBuilder.json(json: Json): JwkParserBuilder =
    this.json(JvmJsonSerializer(json = json))

@ExperimentalJwtApi
internal class JvmJsonSerializer internal constructor(
    private val json: Json
) : Serializer<Map<String, *>>,
    Deserializer<Map<String, *>> {

    @Deprecated("Deprecated in Java")
    override fun serialize(map: Map<String, *>): ByteArray =
        map.formatAsJsonString().encodeToByteArray()

    override fun serialize(map: Map<String, *>, out: OutputStream) {
        val bytes = map.formatAsJsonString().encodeToByteArray()

        out.write(bytes)
    }

    @Deprecated("Deprecated in Java")
    override fun deserialize(bytes: ByteArray): Map<String, Any> {
        val jsonString = bytes.decodeToString()

        return json.decodeFromString(
            deserializer = JsonObject.serializer(),
            string = jsonString
        )
    }

    override fun deserialize(reader: Reader): Map<String, Any> {
        val jsonString = reader.readText()

        return json.decodeFromString(
            deserializer = JsonObject.serializer(),
            string = jsonString
        )
    }

    private fun Any?.formatAsJsonString(): String {
        val element = this.toJsonElement()

        return json.encodeToString(
            serializer = JsonElement.serializer(),
            value = element
        )
    }
}
