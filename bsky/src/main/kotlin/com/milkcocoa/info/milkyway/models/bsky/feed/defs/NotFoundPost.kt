package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.feed.Post
import com.milkcocoa.info.milkyway.types.PostType
import kotlinx.serialization.Serializable

@Serializable
data class NotFoundPost(
    val uri: String,
    val notFound: Boolean = true
) : Post() {
    override val type: PostType
        get() = PostType.NotFoundPost
}