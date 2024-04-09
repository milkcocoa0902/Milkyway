package com.milkcocoa.info.milkyway.models.bsky.feed

import kotlinx.serialization.Serializable

@Serializable
data class ViewerState(
    val repost: String? = null,
    val like: String? = null,
    val replyDisabled: Boolean? = null
)