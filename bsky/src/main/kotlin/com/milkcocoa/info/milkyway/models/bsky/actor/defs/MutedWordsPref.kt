package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.Serializable

@Serializable
data class MutedWordsPref(
    val items: List<MutedWordTarget>
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.MutedWordsPref
}