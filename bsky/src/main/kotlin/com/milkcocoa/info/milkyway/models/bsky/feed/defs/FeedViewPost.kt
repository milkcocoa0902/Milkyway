package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import kotlinx.serialization.Serializable

@Serializable
data class FeedViewPost(
    val post: PostView,
    val reason: Reason? = null,
    val reply: ReplyRef? = null
)