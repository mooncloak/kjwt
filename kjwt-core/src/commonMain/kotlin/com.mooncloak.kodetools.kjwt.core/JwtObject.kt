package com.mooncloak.kodetools.kjwt.core

import com.mooncloak.kodetools.kjwt.core.JwtObject.Builder
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.serializer
import kotlin.properties.ReadOnlyProperty
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
public abstract class JwtObject internal constructor() : Map<String, JsonElement> {

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

    public inline fun <reified T> getValue(
        key: String,
        deserializer: DeserializationStrategy<T> = json.serializersModule.serializer()
    ): T? {
        val element = get(key = key)

        return element?.let {
            json.decodeFromJsonElement(
                deserializer = deserializer,
                element = element
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is JwtObject) return false

        if (json != other.json) return false

        return properties == other.properties
    }

    override fun hashCode(): Int {
        var result = json.hashCode()
        result = 31 * result + properties.hashCode()
        return result
    }

    /**
     * A component that can be used to create an instance of a [JwtObject].
     */
    public abstract class Builder internal constructor(
        initialValues: Map<String, JsonElement> = emptyMap()
    ) : JwtObject(),
        MutableMap<String, JsonElement> {

        override val properties: MutableMap<String, JsonElement> =
            mutableMapOf<String, JsonElement>().apply {
                putAll(initialValues)
            }

        override val keys: MutableSet<String>
            get() = properties.keys

        override val values: MutableCollection<JsonElement>
            get() = properties.values

        override val entries: MutableSet<MutableMap.MutableEntry<String, JsonElement>>
            get() = properties.entries

        override fun put(key: String, value: JsonElement): JsonElement? =
            properties.put(key, value)

        override fun putAll(from: Map<out String, JsonElement>) {
            properties.putAll(from)
        }

        override fun remove(key: String): JsonElement? =
            properties.remove(key)

        override fun clear() {
            properties.clear()
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

        public inline fun <reified T> putValue(
            key: String,
            value: T,
            serializer: KSerializer<T> = json.serializersModule.serializer()
        ) {
            val element = json.encodeToJsonElement(
                serializer = serializer,
                value = value
            )

            set(key, element)
        }

        /**
         * A property delegate to the stored underlying value.
         */
        @ExperimentalJwtApi
        public inline fun <reified V> JwtObject.property(
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
        @ExperimentalJwtApi
        public inline fun <reified V> JwtObject.property(
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
 * A property delegate to the stored underlying value.
 */
@ExperimentalJwtApi
public inline fun <reified V> JwtObject.property(
    key: String? = null,
    defaultValue: V,
    deserializer: DeserializationStrategy<V> = json.serializersModule.serializer()
): ReadOnlyProperty<JwtObject, V> = ReadOnlyRequiredJwtObjectPropertyDelegate(
    key = key,
    initialValue = defaultValue,
    json = json,
    deserializer = deserializer
)

/**
 * A property delegate to the stored underlying value.
 */
@ExperimentalJwtApi
public inline fun <reified V> JwtObject.property(
    key: String? = null,
    deserializer: DeserializationStrategy<V> = json.serializersModule.serializer()
): ReadOnlyProperty<JwtObject, V?> = ReadOnlyNullableJwtObjectPropertyDelegate(
    key = key,
    json = json,
    deserializer = deserializer
)

/**
 * Converts this [JwtObject] into a [JsonObject] instance.
 */
@ExperimentalJwtApi
public fun JwtObject.toJsonObject(): JsonObject =
    JsonObject(content = this)

@ExperimentalJwtApi
@PublishedApi
internal class ReadOnlyRequiredJwtObjectPropertyDelegate<T : JwtObject, V> @PublishedApi internal constructor(
    private val key: String? = null,
    private val initialValue: V,
    private val json: Json,
    private val deserializer: DeserializationStrategy<V>
) : ReadOnlyProperty<T, V> {

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        val key = key ?: property.name

        return thisRef[key]?.let { element ->
            json.decodeFromJsonElement(
                deserializer = deserializer,
                element = element
            )
        } ?: initialValue
    }
}

@ExperimentalJwtApi
@PublishedApi
internal class ReadOnlyNullableJwtObjectPropertyDelegate<T : JwtObject, V> @PublishedApi internal constructor(
    private val key: String? = null,
    private val json: Json,
    private val deserializer: DeserializationStrategy<V>
) : ReadOnlyProperty<T, V?> {

    override fun getValue(thisRef: T, property: KProperty<*>): V? {
        val key = key ?: property.name

        return thisRef[key]?.let { element ->
            json.decodeFromJsonElement(
                deserializer = deserializer,
                element = element
            )
        }
    }
}

@ExperimentalJwtApi
@PublishedApi
internal class RequiredJwtObjectBuilderPropertyDelegate<T : Builder, V> @PublishedApi internal constructor(
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
internal class NullableJwtObjectBuilderPropertyDelegate<T : Builder, V> @PublishedApi internal constructor(
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

@ExperimentalJwtApi
internal abstract class BaseJwtObjectSerializer<T : JwtObject> internal constructor() :
    KSerializer<T> {

    override val descriptor: SerialDescriptor
        get() = JsonObject.serializer().descriptor

    abstract fun toType(json: Json, jsonObject: JsonObject): T

    final override fun serialize(encoder: Encoder, value: T) {
        val jsonObject = value.toJsonObject()

        encoder.encodeSerializableValue(
            serializer = JsonObject.serializer(),
            value = jsonObject
        )
    }

    final override fun deserialize(decoder: Decoder): T {
        val jsonDecoder = (decoder as? JsonDecoder)
            ?: error("JwtObject can only be decoded using a JsonDecoder.")

        val jsonObject = decoder.decodeSerializableValue(
            deserializer = JsonObject.serializer()
        )

        return toType(
            json = jsonDecoder.json,
            jsonObject = jsonObject
        )
    }
}
