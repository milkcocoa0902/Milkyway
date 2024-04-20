package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord.Companion.CreatedAtSerializer
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
 * Record declaring a 'block' relationship against another account. NOTE: blocks are public in Bluesky; see blog posts for details.
 */
@SerialName("app.bsky.graph.block")
@Serializable
data class BlockRecord(
    /**
     * DID of the account to be blocked.
     */
    val subject: String,
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.BlockRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}