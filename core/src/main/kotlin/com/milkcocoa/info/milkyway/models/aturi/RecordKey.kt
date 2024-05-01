package com.milkcocoa.info.milkyway.models.aturi

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = RecordKey.Companion.RecordKeySerializer::class)
data class RecordKey(
    val value: String
) {
    companion object {
        const val RECORD_KEY_REGEX_PATTERN = "[A-Za-z0-9.-_:~]{1,512}"

        object RecordKeySerializer : KSerializer<RecordKey> {
            override val descriptor: SerialDescriptor
                get() = PrimitiveSerialDescriptor("RecordKey", PrimitiveKind.STRING)

            override fun deserialize(decoder: Decoder): RecordKey {
                return RecordKey(decoder.decodeString())
            }

            override fun serialize(
                encoder: Encoder,
                value: RecordKey
            ) {
                encoder.encodeString(value.value)
            }
        }
    }

    init {
        RECORD_KEY_REGEX_PATTERN.toRegex().matchEntire(value) ?: error("Invalid value $value")
    }
}