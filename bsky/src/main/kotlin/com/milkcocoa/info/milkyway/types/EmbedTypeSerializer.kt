package com.milkcocoa.info.milkyway.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class EmbedTypeSerializer() : KSerializer<EmbedType> {
    override val descriptor: SerialDescriptor get() = PrimitiveSerialDescriptor("\$type", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): EmbedType {
        return EmbedType.getByIdentifier(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: EmbedType) {
        encoder.encodeString(value.identifier)
    }
}