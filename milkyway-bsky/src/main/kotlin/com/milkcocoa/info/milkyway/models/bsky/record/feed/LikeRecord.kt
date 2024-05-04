package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.repo.StrongRef
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Record declaring a 'like' of a piece of subject content.
 */
@SerialName("app.bsky.feed.like")
@Serializable
data class LikeRecord(
    val subject: StrongRef,
    /**
     * Client-declared timestamp when this post was originally created.
     */
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.LikeRecord
}