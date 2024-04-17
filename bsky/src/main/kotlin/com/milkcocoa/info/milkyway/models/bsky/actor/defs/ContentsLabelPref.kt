package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
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

    @Serializable
    enum class ContentLabelVisibility{
        @SerialName("ignore")
        LabelVisibilityIgnore,
        @SerialName("show")
        LabelVisibilityShow,
        @SerialName("warn")
        LabelVisibilityWarn,
        @SerialName("hide")
        LabelVisibilityHide
        ;
    }
}