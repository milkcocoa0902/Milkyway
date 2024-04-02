package com.milkcocoa.info.milkyway.models.bsky.feed

import kotlinx.serialization.Serializable

@Serializable
data class Viewer(
    val repost: String? = null,
    val like: String? = null,
    val replyDisabled: Boolean? = null
)