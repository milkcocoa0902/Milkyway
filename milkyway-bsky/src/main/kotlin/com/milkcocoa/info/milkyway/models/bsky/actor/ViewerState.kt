package com.milkcocoa.info.milkyway.models.bsky.actor

import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListViewBasic
import kotlinx.serialization.Serializable

@Serializable
data class ViewerState(
    val muted: Boolean? = null,
    val mutedByList: List<ListViewBasic> = emptyList(),
    val blockedBy: Boolean? = null,
    val blocking: AtUri? = null,
    val blockingByList: List<ListViewBasic> = emptyList(),
    val following: AtUri? = null,
    val followedBy: AtUri? = null
)