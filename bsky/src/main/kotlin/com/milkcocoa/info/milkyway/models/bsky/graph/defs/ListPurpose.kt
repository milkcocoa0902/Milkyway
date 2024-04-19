package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ListPurpose {
    /**
     * A list of actors to apply an aggregate moderation action (mute/block) on.
     */
    @SerialName("app.bsky.graph.defs#modlist")
    ModList,

    /**
     * A list of actors used for curation purposes such as list feeds or interaction gating.
     */
    @SerialName("app.bsky.graph.defs#curatelist")
    CurateList
}