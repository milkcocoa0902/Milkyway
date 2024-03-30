package com.milkcocoa.info.milkyway.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class EmbedRecordViewTypeSerializer() : KSerializer<EmbedRecordViewType> {
    override val descriptor: SerialDescriptor get() = PrimitiveSerialDescriptor("\$type", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): EmbedRecordViewType {
        return EmbedRecordViewType.getByIdentifier(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: EmbedRecordViewType) {
        encoder.encodeString(value.identifier)
    }
}