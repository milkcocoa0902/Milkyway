package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.label.defs.SelfLabels
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.models.entity.Blob
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.time.LocalDateTime

/**
 * Record representing a list of accounts (actors). Scope includes both moderation-oriented lists and curration-oriented lists.
 */
@SerialName("app.bsky.graph.list")
@Serializable
data class ListRecord(
    /**
     *
     */
    val purpose: String,
    val name: String,
    val description: String = "",
    val descriptionFacets: List<Facet> = emptyList(),
    val avatar: Blob? = null,
    val labels: SelfLabels? = null,
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.ListRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}