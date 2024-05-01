package com.milkcocoa.info.milkyway.models.aturi

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = ATIdentifier.AtIdentifierSerializer::class)
sealed class ATIdentifier() {
    @Serializable
    enum class IdentifierType {
        Handle,
        Did
    }

    object AtIdentifierSerializer : JsonContentPolymorphicSerializer<ATIdentifier>(ATIdentifier::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ATIdentifier> {
            if (Did.DID_REGEX_PATTERN.toRegex().matchEntire(element.jsonPrimitive.content) != null)
                {
                    return Did.serializer()
                } else if (Handle.HANDLE_REGEX_PATTERN.toRegex().matchEntire(element.jsonPrimitive.content) != null)
                {
                    return Handle.serializer()
                } else {
                throw SerializationException("Unsupported element type ${element.jsonPrimitive.content}")
            }
        }
    }
}

class AtIdentifierListSerializer : KSerializer<List<ATIdentifier>> {
    private val delegateSerializer = ListSerializer(ATIdentifier.serializer())
    override val descriptor: SerialDescriptor
        get() =
            buildClassSerialDescriptor("ATIdentifierList") {
                element("key", delegateSerializer.descriptor)
            }

    override fun deserialize(decoder: Decoder): List<ATIdentifier> {
        return decoder.decodeSerializableValue(delegateSerializer)
    }

    override fun serialize(
        encoder: Encoder,
        value: List<ATIdentifier>
    ) {
        encoder.encodeSerializableValue(delegateSerializer, value)
    }
}