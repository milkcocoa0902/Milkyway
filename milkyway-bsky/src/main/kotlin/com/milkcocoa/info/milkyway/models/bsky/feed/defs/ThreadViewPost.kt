package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.feed.Post
import com.milkcocoa.info.milkyway.types.PostType
import kotlinx.serialization.Serializable

@Serializable
data class ThreadViewPost(
    val post: PostView,
    val parent: Post? = null,
    val replies: List<Post> = emptyList()
) : Post() {
    override val type: PostType
        get() = PostType.ThreadViewPost
}