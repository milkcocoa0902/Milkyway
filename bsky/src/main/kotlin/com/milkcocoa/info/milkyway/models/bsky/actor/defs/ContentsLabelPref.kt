package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class ContentsLabelPref(
    val labelerDid: String? = null,
    val label: String,
    val visibility: ContentLabelVisibility
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.ContentsLabelPref

    @Serializable(with = ContentLabelVisibility.Companion.Serializer::class)
    enum class ContentLabelVisibility(val identifier: String) {
        LabelVisibilityIgnore("ignore"),
        LabelVisibilityShow("show"),
        LabelVisibilityWarn("warn"),
        LabelVisibilityHide("hide"),
        Unknown("unknown")
        ;

        companion object {
            fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

            object Serializer : KSerializer<ContentLabelVisibility> {
                override val descriptor: SerialDescriptor get() =
                    PrimitiveSerialDescriptor(
                        "visibility",
                        PrimitiveKind.STRING
                    )

                override fun deserialize(decoder: Decoder): ContentLabelVisibility {
                    val value = decoder.decodeString()
                    return ContentLabelVisibility.entries
                        .firstOrNull { it.identifier == value } ?: throw NoSuchElementException()
                }

                override fun serialize(
                    encoder: Encoder,
                    value: ContentLabelVisibility
                ) {
                    encoder.encodeString(value.identifier)
                }
            }
        }
    }
}