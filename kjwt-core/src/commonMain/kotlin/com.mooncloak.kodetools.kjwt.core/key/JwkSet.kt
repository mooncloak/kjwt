package com.mooncloak.kodetools.kjwt.core.key

import com.mooncloak.kodetools.kjwt.core.BaseJwtObjectSerializer
import com.mooncloak.kodetools.kjwt.core.JwtObject
import com.mooncloak.kodetools.kjwt.core.property
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Represents a JWK (JSON Web Key) Set.
 *
 * @property [keys] A [List] of [Jwk] key values that this [JwkSet] contains.
 *
 * @see [JWK Specification](https://datatracker.ietf.org/doc/html/rfc7517)
 */
@Serializable(with = JwkSetSerializer::class)
@ExperimentalJwtApi
public class JwkSet public constructor(
    override val json: Json = Json.Default,
    override val properties: Map<String, JsonElement>,
    keys: List<Jwk>
) : JwtObject() {

    public val jwkKeys: List<Jwk> by property(
        key = PropertyKey.KEYS,
        defaultValue = keys
    )

    override fun toString(): String =
        "JwkSet(json=$json, properties=$properties)"

    public object PropertyKey {

        public const val KEYS: String = "keys"
    }

    /**
     * A builder component for creating a [JwkSet] instance. This component should not be created
     * directly, but instead can be used to create a [JwkSet] instance via the [JwkSet] constructor
     * function.
     */
    public class Builder internal constructor(
        initialKeys: List<Jwk>,
        override val json: Json,
        initialValues: Map<String, JsonElement> = emptyMap()
    ) : JwtObject.Builder(initialValues) {

        @Suppress("MemberVisibilityCanBePrivate")
        public var jwkKeys: List<Jwk> by property(
            key = PropertyKey.KEYS,
            initialValue = initialKeys
        )

        /**
         * Converts this [JwkSet.Builder] instance into a [JwkSet] instance.
         */
        public fun build(): JwkSet =
            JwkSet(
                json = json,
                properties = properties,
                keys = jwkKeys
            )
    }

    public companion object
}

/**
 * Converts this [JwkSet] instance into a [JwkSet.Builder].
 */
@ExperimentalJwtApi
public fun JwkSet.toBuilder(): JwkSet.Builder =
    JwkSet.Builder(
        json = this.json,
        initialValues = this.properties,
        initialKeys = jwkKeys
    )

/**
 * Creates a new [JwkSet] instance starting with the same values from this [JwkSet] instance which
 * can be overridden from the provided builder [block].
 */
@ExperimentalJwtApi
public fun JwkSet.copy(block: JwkSet.Builder.() -> Unit = {}): JwkSet {
    val builder = this.toBuilder().apply(block)

    return builder.build()
}

/**
 * Creates a [JwkSet] from the provided builder [block] and [json] instance.
 */
@ExperimentalJwtApi
public fun JwkSet.Companion.build(
    keys: List<Jwk> = emptyList(),
    json: Json = Json.Default,
    block: JwkSet.Builder.() -> Unit
): JwkSet = JwkSet.Builder(
    json = json,
    initialKeys = keys
).apply(block).build()

/**
 * Creates a [JwkSet] from the provided builder [block] and [json] instance. This is a convenience
 * function for invoking the [build] function.
 */
@ExperimentalJwtApi
public operator fun JwkSet.Companion.invoke(
    keys: List<Jwk> = emptyList(),
    json: Json = Json.Default,
    block: JwkSet.Builder.() -> Unit
): JwkSet = build(
    keys = keys,
    json = json,
    block = block
)

/**
 * The [KSerializer] implementation for the [Jwk] class.
 */
@ExperimentalJwtApi
internal object JwkSetSerializer : BaseJwtObjectSerializer<JwkSet>() {

    override fun toType(json: Json, jsonObject: JsonObject): JwkSet {
        val keys = jsonObject[JwkSet.PropertyKey.KEYS]
            ?.jsonArray
            ?.mapNotNull { it.jsonObject }
            ?.map { jwk ->
                val keyType = jsonObject[Jwk.PropertyKey.KEY_TYPE]
                    ?.jsonPrimitive
                    ?.contentOrNull
                    ?.let {
                        KeyType(value = it)
                    }
                    ?: error("Jwk must have a Key Type property.")

                Jwk(
                    json = json,
                    properties = jwk,
                    keyType = keyType
                )
            } ?: emptyList()

        return JwkSet(
            json = json,
            properties = jsonObject,
            keys = keys
        )
    }
}
