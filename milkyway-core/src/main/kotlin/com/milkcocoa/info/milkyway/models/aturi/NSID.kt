package com.milkcocoa.info.milkyway.models.aturi

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = NSID.Companion.NSIDSerializer::class)
data class NSID(
    val value: String
) {
    companion object {
        @Suppress("ktlint:standard:max-line-length")
        const val NSID_REGEX_PATTERN = "[a-zA-Z]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(\\.[a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+(\\.[a-zA-Z]([a-zA-Z]{0,61}[a-zA-Z])?)"

        object NSIDSerializer : KSerializer<NSID> {
            override val descriptor: SerialDescriptor
                get() = PrimitiveSerialDescriptor("NSID", PrimitiveKind.STRING)

            override fun deserialize(decoder: Decoder): NSID {
                return NSID(decoder.decodeString())
            }

            override fun serialize(
                encoder: Encoder,
                value: NSID
            ) {
                encoder.encodeString(value.value)
            }
        }
    }

    init {
        NSID_REGEX_PATTERN.toRegex().matchEntire(value) ?: error("")
    }
}