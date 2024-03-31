package com.milkcocoa.info.milkyway.models.bsky.feed

import com.milkcocoa.info.milkyway.models.bsky.embed.view.EmbedView
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import kotlinx.serialization.Serializable

@Serializable()
data class Post(
    val uri: String,
    val cid: String,
    val author: Author,
    val record: BskyRecord,
    val embed: EmbedView? = null,
    val replyCount: Int?,
    val repostCount: Int?,
    val likeCount: Int?,
    val indexedAt: String,
    val viewer: Viewer? = null,
    val labels: List<String>?,
//    val threadgate: String,
)