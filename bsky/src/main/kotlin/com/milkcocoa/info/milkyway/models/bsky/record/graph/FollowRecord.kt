package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord.Companion.CreatedAtSerializer
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class FollowRecord(
    /**
     * did
     */
    val subject: String,
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime,
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.FollowRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}