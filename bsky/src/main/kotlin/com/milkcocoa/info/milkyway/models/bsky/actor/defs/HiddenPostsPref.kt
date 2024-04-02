package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.Serializable

@Serializable
data class HiddenPostsPref(
    val items: List<String>
): ActorPreferenceDef(){
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.HiddenPostsPref
}
