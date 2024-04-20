package com.milkcocoa.info.milkyway.models.bsky.record.graph

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord
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
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.ListItemRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}