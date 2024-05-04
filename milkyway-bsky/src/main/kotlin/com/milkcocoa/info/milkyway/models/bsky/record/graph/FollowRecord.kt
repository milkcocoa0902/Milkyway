package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@SerialName("app.bsky.graph.follow")
@Serializable
data class FollowRecord(
    /**
     * did
     */
    val subject: String,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.FollowRecord
}