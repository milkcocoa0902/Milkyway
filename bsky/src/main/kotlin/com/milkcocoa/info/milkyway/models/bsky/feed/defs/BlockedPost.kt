package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.feed.Post
import com.milkcocoa.info.milkyway.types.PostType
import kotlinx.serialization.Serializable

@Serializable
data class BlockedPost(
    val uri: String,
    val blocked: Boolean,
    val author: BlockedAuthor
): Post(){

    override val type: PostType
        get() = PostType.BlockedPost
}