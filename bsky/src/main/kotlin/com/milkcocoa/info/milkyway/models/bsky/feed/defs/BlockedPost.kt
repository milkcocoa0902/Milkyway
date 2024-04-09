package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.actor.ViewerState
import com.milkcocoa.info.milkyway.models.bsky.feed.Post
import com.milkcocoa.info.milkyway.types.PostType
import kotlinx.serialization.Serializable

@Serializable
data class BlockedPost(
    val uri: String,
    val blocked: Boolean,
    val author: BlockedAuthor
): Post(){

    @Serializable
    data class BlockedAuthor(
        val did: String,
        val viewer: ViewerState? = null
    )

    override val type: PostType
        get() = PostType.BlockedPost
}