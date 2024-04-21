package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.types.RecordType
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
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.ListBlockRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}