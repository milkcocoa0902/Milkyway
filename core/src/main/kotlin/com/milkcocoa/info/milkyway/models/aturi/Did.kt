package com.milkcocoa.info.milkyway.models.aturi

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = Did.Companion.DidSerializer::class)
data class Did(
    val value: String
) : ATIdentifier() {
    companion object {
        const val DID_REGEX_PATTERN = "did:[a-z]+:[a-zA-Z0-9._:%-]*[a-zA-Z0-9._-]"

        object DidSerializer : KSerializer<Did> {
            override val descriptor: SerialDescriptor
                get() = PrimitiveSerialDescriptor("Did", PrimitiveKind.STRING)

            override fun deserialize(decoder: Decoder): Did {
                return Did(decoder.decodeString())
            }

            override fun serialize(
                encoder: Encoder,
                value: Did
            ) {
                encoder.encodeString(value.value)
            }
        }
    }

    init {
        DID_REGEX_PATTERN.toRegex().matchEntire(value) ?: error("")
    }
}