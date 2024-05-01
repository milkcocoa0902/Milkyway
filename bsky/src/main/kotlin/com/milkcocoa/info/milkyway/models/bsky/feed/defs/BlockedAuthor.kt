package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.bsky.actor.ViewerState
import kotlinx.serialization.Serializable

@Serializable
data class BlockedAuthor(
    val did: Did,
    val viewer: ViewerState? = null
)