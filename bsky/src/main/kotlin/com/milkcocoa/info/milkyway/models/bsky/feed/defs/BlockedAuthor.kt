package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.actor.ViewerState
import kotlinx.serialization.Serializable

@Serializable
data class BlockedAuthor(
    val did: String,
    val viewer: ViewerState? = null
)