package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.Serializable

@Serializable
data class SavedFeedsPref(
    val pinned: List<AtUri>,
    val saved: List<AtUri>,
    val timelineIndex: Int? = null
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.SavedFeedsPref
}