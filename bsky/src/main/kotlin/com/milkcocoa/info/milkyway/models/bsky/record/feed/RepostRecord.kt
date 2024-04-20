package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.repo.StringRef
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.time.LocalDateTime

/**
 * Record representing a 'repost' of an existing Bluesky post.
 */
@SerialName("app.bsky.feed.repost")
@Serializable
data class RepostRecord(
    val subject: StringRef,
    /**
     * Client-declared timestamp when this post was originally created.
     */
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.RepostRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}