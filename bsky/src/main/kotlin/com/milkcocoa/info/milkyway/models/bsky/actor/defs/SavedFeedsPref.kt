package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.Serializable

@Serializable
data class SavedFeedsPref(
    val pinned: List<String>,
    val saved: List<String>,
    val timelineIndex: Int? = null
): ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.SavedFeedsPref
}