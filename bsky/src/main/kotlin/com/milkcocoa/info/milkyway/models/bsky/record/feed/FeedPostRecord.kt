package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.bsky.embed.Embed
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class FeedPostRecord(
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime,
    val embed: Embed? = null,
    val facets: List<Facet> = emptyList(),
    val langs: List<String> = emptyList(),
    val text: String
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.FeedPostRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}