package com.milkcocoa.info.milkyway.types

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class EmbedViewTypeSerializer() : KSerializer<RecordEmbedViewRecordType> {
    override val descriptor: SerialDescriptor get() = PrimitiveSerialDescriptor("\$type", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): RecordEmbedViewRecordType {
        return RecordEmbedViewRecordType.getByIdentifier(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: RecordEmbedViewRecordType) {
        encoder.encodeString(value.identifier)
    }
}