package com.milkcocoa.info.milkyway.models.bsky.record.feed

import com.milkcocoa.info.milkyway.models.bsky.feed.threadgate.GateRule
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ThreadGateRecord(
    val allow: List<GateRule>,
    @Serializable(with = CreatedAtSerializer::class)
    val createdAt: LocalDateTime,
    val post: String
) : BskyRecord() {
    override val type: RecordType
        get() = RecordType.ThreadGateRecord

    companion object {
        object CreatedAtSerializer : DateTimeSerializer("createdAt")
    }
}