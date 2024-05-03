package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.Serializable

@Serializable
data class HiddenPostsPref(
    val items: List<AtUri>
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.HiddenPostsPref
}