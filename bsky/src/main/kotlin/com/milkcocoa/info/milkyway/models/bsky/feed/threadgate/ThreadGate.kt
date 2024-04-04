package com.milkcocoa.info.milkyway.models.bsky.feed.threadgate

import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import kotlinx.serialization.Serializable

@Serializable
data class ThreadGate(
    val uri: String,
    val cid: String,
    val record: BskyRecord,
    val lists: List<ActorListElement>
)