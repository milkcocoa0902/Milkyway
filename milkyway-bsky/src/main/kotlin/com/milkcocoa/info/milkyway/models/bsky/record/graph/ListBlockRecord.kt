package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Record representing a block relationship against an entire an entire list of accounts (actors).
 */
@SerialName("app.bsky.graph.list.block")
@Serializable
data class ListBlockRecord(
    /**
     * Reference (AT-URI) to the mod list record.
     */
    val subject: String,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.ListBlockRecord
}