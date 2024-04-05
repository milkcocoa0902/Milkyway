package com.milkcocoa.info.milkyway.models.bsky.feed

import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileViewBasic
import com.milkcocoa.info.milkyway.models.bsky.embed.view.EmbedView
import com.milkcocoa.info.milkyway.models.bsky.feed.threadgate.ThreadGate
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.entity.Label
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable()
data class Post(
    val uri: String,
    val cid: String,
    val author: ProfileViewBasic,
    val record: BskyRecord,
    val embed: EmbedView? = null,
    val replyCount: Int?,
    val repostCount: Int?,
    val likeCount: Int?,
    @Serializable(with = IndexedAtSerializer::class)
    val indexedAt: LocalDateTime,
    val viewer: Viewer? = null,
    val labels: List<Label>? = emptyList(),
    val threadgate: ThreadGate? = null
) {
    companion object {
        object IndexedAtSerializer : DateTimeSerializer("indexedAt")
    }
}