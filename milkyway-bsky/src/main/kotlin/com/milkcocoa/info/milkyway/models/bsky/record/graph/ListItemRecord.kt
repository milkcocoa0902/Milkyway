package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Record representing an account's inclusion on a specific list. The AppView will ignore duplicate listitem records.
 */
@SerialName("app.bsky.graph.list.item")
@Serializable
data class ListItemRecord(
    /**
     * The account which is included on the list.
     */
    val subject: String,
    /**
     * Reference (AT-URI) to the list record (app.bsky.graph.list).
     */
    val list: String,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.ListItemRecord
}