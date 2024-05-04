package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileViewBasic
import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.EmbedView
import com.milkcocoa.info.milkyway.models.bsky.feed.Post
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.types.PostType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable()
data class PostView(
    val uri: AtUri,
    val cid: String,
    val author: ProfileViewBasic,
    val record: Record<BskyRecordType>,
    val embed: EmbedView? = null,
    val replyCount: Int?,
    val repostCount: Int?,
    val likeCount: Int?,
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime,
    val viewer: ViewerState? = null,
    val labels: List<Label>? = emptyList(),
    val threadgate: ThreadGateView? = null
) : Post() {
    override val type: PostType
        get() = PostType.Post
}