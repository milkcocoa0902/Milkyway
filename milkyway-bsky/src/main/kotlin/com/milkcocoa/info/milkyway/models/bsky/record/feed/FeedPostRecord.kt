package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.label.defs.SelfLabels
import com.milkcocoa.info.milkyway.models.atproto.repo.StrongRef
import com.milkcocoa.info.milkyway.models.bsky.embed.Embed
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@SerialName("app.bsky.feed.post")
data class FeedPostRecord(
    /**
     * The primary post content. May be an empty string, if there are embeds
     */
    val text: String,
    /**
     * Annotations of text (mentions, URLs, hashtags, etc)
     */
    val facets: List<Facet> = emptyList(),
    val reply: ReplyRef? = null,
    val embed: Embed? = null,
    /**
     * Indicates human language of post primary text content.
     */
    val langs: List<String> = emptyList(),
    /**
     * Self-label values for this post. Effectively content warnings.
     */
    val labels: SelfLabels? = null,
    /**
     * Additional hashtags, in addition to any included in post text and facets.
     */
    val tags: List<String> = emptyList(),
    /**
     * Client-declared timestamp when this post was originally created.
     */
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.FeedPostRecord

    @Serializable
    data class ReplyRef(
        val root: StrongRef,
        val parent: StrongRef
    )
}