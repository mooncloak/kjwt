package com.mooncloak.kodetools.kjwt.core

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Transient
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.serializer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Represents a decoded [JsonObject] used for JSON Web Token purposes. For instance, this could
 * represent a [JsonElement] that is stored in a decoded JWT/JWS Token, because, in a JWT, a Header
 * is a JSON Object, and the Body (or Payload) can be a JSON Object as well.
 *
 * Typically, JWT object constructs are extensible JSON objects. So this interface provides a
 * simple way to extracting extra data from the object.
 */
@ExperimentalJwtApi
public sealed interface JwtObject : Map<String, JsonElement> {

    /**
     * A component that can be used to create an instance of a [JwtObject].
     */
    public abstract class Builder internal constructor(
        initialValues: Map<String, JsonElement> = emptyMap()
    ) : BaseJwtObject(),
        JwtObject,
        MutableMap<String, JsonElement> {

        override val properties: MutableMap<String, JsonElement>
            get() = storage

        override val keys: MutableSet<String>
            get() = storage.keys

        override val values: MutableCollection<JsonElement>
            get() = storage.values

        override val entries: MutableSet<MutableMap.MutableEntry<String, JsonElement>>
            get() = storage.entries

        private val storage: MutableMap<String, JsonElement> =
            mutableMapOf<String, JsonElement>().apply {
                putAll(initialValues)
            }

        override fun put(key: String, value: JsonElement): JsonElement? =
            storage.put(key, value)

        override fun putAll(from: Map<out String, JsonElement>) {
            storage.putAll(from)
        }

        override fun remove(key: String): JsonElement? =
            storage.remove(key)

        override fun clear() {
            storage.clear()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Builder) return false

            if (json != other.json) return false

            return properties == other.properties
        }

        override fun hashCode(): Int {
            var result = json.hashCode()
            result = 31 * result + properties.hashCode()
            return result
        }

        override fun toString(): String =
            "Builder(json=$json, properties=$properties)"

        public inline fun <reified T> setValue(
            key: String,
            value: T
        ) {
            val element = json.encodeToJsonElement(value)

            set(key, element)
        }

        /**
         * A property delegate to the stored underlying value.
         */
        protected inline fun <reified V> property(
            key: String? = null,
            initialValue: V,
            serializer: SerializationStrategy<V> = json.serializersModule.serializer(),
            deserializer: DeserializationStrategy<V> = json.serializersModule.serializer()
        ): ReadWriteProperty<Builder, V> = RequiredJwtObjectBuilderPropertyDelegate(
            key = key,
            initialValue = initialValue,
            json = json,
            serializer = serializer,
            deserializer = deserializer
        )

        /**
         * A property delegate to the stored underlying value.
         */
        protected inline fun <reified V> property(
            key: String? = null,
            serializer: SerializationStrategy<V> = json.serializersModule.serializer(),
            deserializer: DeserializationStrategy<V> = json.serializersModule.serializer()
        ): ReadWriteProperty<Builder, V?> = NullableJwtObjectBuilderPropertyDelegate(
            key = key,
            json = json,
            serializer = serializer,
            deserializer = deserializer
        )

        public companion object
    }

    public companion object
}

/**
 * Converts this [JwtObject] into a [JsonObject] instance.
 */
@ExperimentalJwtApi
public fun JwtObject.toJsonObject(): JsonObject =
    JsonObject(content = this)

@ExperimentalJwtApi
public abstract class BaseJwtObject : JwtObject {

    @PublishedApi
    internal abstract val json: Json

    internal abstract val properties: Map<String, JsonElement>

    @Transient
    final override val size: Int
        get() = properties.size

    @Transient
    override val entries: Set<Map.Entry<String, JsonElement>>
        get() = properties.entries

    @Transient
    override val keys: Set<String>
        get() = properties.keys

    @Transient
    override val values: Collection<JsonElement>
        get() = properties.values

    final override fun containsKey(key: String): Boolean = properties.containsKey(key = key)

    final override fun containsValue(value: JsonElement): Boolean =
        properties.containsValue(value = value)

    final override fun get(key: String): JsonElement? = properties.get(key = key)

    final override fun isEmpty(): Boolean = properties.isEmpty()

    public inline fun <reified T> getValue(key: String): T? {
        val element = get(key = key)

        return element?.let { json.decodeFromJsonElement(element) }
    }
}

@ExperimentalJwtApi
@PublishedApi
internal class RequiredJwtObjectBuilderPropertyDelegate<T : JwtObject.Builder, V> @PublishedApi internal constructor(
    private val key: String? = null,
    private val initialValue: V,
    private val json: Json,
    private val serializer: SerializationStrategy<V>,
    private val deserializer: DeserializationStrategy<V>
) : ReadWriteProperty<T, V> {

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        val key = key ?: property.name

        return thisRef[key]?.let { element ->
            json.decodeFromJsonElement(
                deserializer = deserializer,
                element = element
            )
        } ?: initialValue
    }

    override fun setValue(thisRef: T, property: KProperty<*>, value: V) {
        val key = key ?: property.name

        if (value == null) {
            thisRef.remove(key)
        } else {
            val jsonElement = json.encodeToJsonElement(
                serializer = serializer,
                value = value
            )

            thisRef[key] = jsonElement
        }
    }
}

@ExperimentalJwtApi
@PublishedApi
internal class NullableJwtObjectBuilderPropertyDelegate<T : JwtObject.Builder, V> @PublishedApi internal constructor(
    private val key: String? = null,
    private val json: Json,
    private val serializer: SerializationStrategy<V>,
    private val deserializer: DeserializationStrategy<V>
) : ReadWriteProperty<T, V?> {

    override fun getValue(thisRef: T, property: KProperty<*>): V? {
        val key = key ?: property.name

        return thisRef[key]?.let { element ->
            json.decodeFromJsonElement(
                deserializer = deserializer,
                element = element
            )
        }
    }

    override fun setValue(thisRef: T, property: KProperty<*>, value: V?) {
        val key = key ?: property.name

        if (value == null) {
            thisRef.remove(key)
        } else {
            val jsonElement = json.encodeToJsonElement(
                serializer = serializer,
                value = value
            )

            thisRef[key] = jsonElement
        }
    }
}
