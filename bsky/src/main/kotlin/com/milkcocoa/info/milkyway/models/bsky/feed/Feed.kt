package com.milkcocoa.info.milkyway.models.bsky.feed

import com.milkcocoa.info.milkyway.models.bsky.embed.EmbedView
import kotlinx.serialization.Serializable

@Serializable()
data class Post(
    val uri: String,
    val cid: String,
    val author: Author,
//    val record: String,
    val embed: EmbedView? = null,
    val replyCount: Int?,
    val repostCount: Int?,
    val likeCount: Int?,
    val indexedAt: String,
    val viewer: Viewer? = null,
    val labels: List<String>?,
//    val threadgate: String,
)

@Serializable
data class Author(
    val did: String,
    val handle: String,
    val displayName: String?,
    val avatar: String?,
    val associated: Associated? = null
//    val viewer: String,
//    val labels: String,
)

@Serializable
data class Associated(
    val lists: Int? = null,
    val feedgens: Int? = null,
    val labeler: Boolean? = null
)


@Serializable
data class Viewer(
    val repost: String? = null,
    val like: String? = null,
    val replyDisabled: Boolean? = null
)

@Serializable
data class FeedEntity(
    val post: Post
)