package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Record declaring a 'block' relationship against another account. NOTE: blocks are public in Bluesky; see blog posts for details.
 */
@SerialName("app.bsky.graph.block")
@Serializable
data class BlockRecord(
    /**
     * DID of the account to be blocked.
     */
    val subject: String,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.BlockRecord
}