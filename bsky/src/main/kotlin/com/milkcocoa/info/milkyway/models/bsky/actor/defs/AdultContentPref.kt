package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.Serializable

@Serializable
data class AdultContentPref(
    val enabled: Boolean
): ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.AdultContentPref
}