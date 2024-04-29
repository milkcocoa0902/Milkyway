package com.milkcocoa.info.milkyway.models.aturi

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = Handle.Companion.HandleSerializer::class)
data class Handle(
    val value: String
) : ATIdentifier() {
    override val type: IdentifierType
        get() = IdentifierType.Handle

    companion object {
        @Suppress("ktlint:standard:max-line-length")
        const val HANDLE_REGEX_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?"

        object HandleSerializer : KSerializer<Handle> {
            override val descriptor: SerialDescriptor
                get() = PrimitiveSerialDescriptor("Handle", PrimitiveKind.STRING)

            override fun deserialize(decoder: Decoder): Handle {
                return Handle(decoder.decodeString())
            }

            override fun serialize(
                encoder: Encoder,
                value: Handle
            ) {
                encoder.encodeString(value.value)
            }
        }
    }

    init {
        HANDLE_REGEX_PATTERN.toRegex().matchEntire(value) ?: error("")
    }
}