package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.label.defs.SelfLabels
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.models.entity.BlobObject
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@SerialName("app.bsky.feed.generator")
@Serializable
data class GeneratorRecord(
    val did: Did,
    val displayName: String,
    val description: String? = null,
    val descriptionFacets: List<Facet>? = null,
    val avatar: BlobObject? = null,
    /**
     * Declaration that a feed accepts feedback interactions from a client through app.bsky.feed.sendInteractions
     */
    val acceptsInteractions: Boolean? = null,
    val labels: SelfLabels? = null,
    /**
     * Client-declared timestamp when this post was originally created.
     */
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.GeneratorRecord
}