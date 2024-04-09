package com.milkcocoa.info.milkyway.models.bsky.actor

import com.milkcocoa.info.milkyway.models.bsky.feed.threadgate.ActorListElement
import kotlinx.serialization.Serializable

@Serializable
data class ViewerState(
    val muted: Boolean? = null,
    val mutedByList: List<ActorListElement> = emptyList(),
    val blockedBy: Boolean? = null,
    val blocking: String? = null,
    val blockingByList: List<ActorListElement> = emptyList(),
    val following: String? = null,
    val followedBy: String? = null
)