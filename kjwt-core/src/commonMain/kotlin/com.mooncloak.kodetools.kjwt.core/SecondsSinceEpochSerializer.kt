package com.mooncloak.kodetools.kjwt.core

import kotlinx.datetime.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal data object SecondsSinceEpochSerializer : KSerializer<Instant> {

    override val descriptor: SerialDescriptor
        get() = Long.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeLong(value.epochSeconds)
    }

    override fun deserialize(decoder: Decoder): Instant {
        val seconds = decoder.decodeLong()

        return Instant.fromEpochSeconds(seconds)
    }
}
