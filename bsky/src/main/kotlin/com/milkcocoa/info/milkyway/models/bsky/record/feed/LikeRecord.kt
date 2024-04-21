package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.atproto.repo.StringRef
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.types.RecordType
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
    val subject: StringRef,
    /**
     * Client-declared timestamp when this post was originally created.
     */
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.LikeRecord
}