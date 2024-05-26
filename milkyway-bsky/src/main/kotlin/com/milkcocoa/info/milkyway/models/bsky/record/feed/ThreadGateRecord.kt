package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.feed.threadgate.GateRule
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@SerialName("app.bsky.feed.threadgate")
@Serializable
data class ThreadGateRecord(
    val allow: List<GateRule>,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime,
    val post: AtUri
) : Record<BskyRecordType> {
    override val type: BskyRecordType
        get() = BskyRecordType.ThreadGateRecord
}