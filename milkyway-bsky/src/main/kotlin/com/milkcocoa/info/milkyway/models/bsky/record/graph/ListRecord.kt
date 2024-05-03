package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.atproto.label.defs.SelfLabels
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.models.entity.BlobObject
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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
    val avatar: BlobObject? = null,
    val labels: SelfLabels? = null,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.ListRecord
}