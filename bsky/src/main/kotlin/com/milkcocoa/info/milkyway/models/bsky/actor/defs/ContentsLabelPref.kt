package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentsLabelPref(
    val labelerDid: Did? = null,
    val label: String,
    val visibility: ContentLabelVisibility
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.ContentsLabelPref

    @Serializable
    enum class ContentLabelVisibility {
        @SerialName("ignore")
        LabelVisibilityIgnore,

        @SerialName("show")
        LabelVisibilityShow,

        @SerialName("warn")
        LabelVisibilityWarn,

        @SerialName("hide")
        LabelVisibilityHide
    }
}