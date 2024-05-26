package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable

@Serializable
data class ViewerState(
    val repost: AtUri? = null,
    val like: AtUri? = null,
    val replyDisabled: Boolean? = null
)