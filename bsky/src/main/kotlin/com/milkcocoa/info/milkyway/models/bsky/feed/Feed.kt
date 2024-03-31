package com.milkcocoa.info.milkyway.models.bsky.feed

import kotlinx.serialization.Serializable

@Serializable
data class Feed(
    val post: Post
)