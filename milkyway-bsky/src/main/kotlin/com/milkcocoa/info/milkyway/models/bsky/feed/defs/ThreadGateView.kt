package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListViewBasic
import com.milkcocoa.info.milkyway.models.bsky.record.feed.ThreadGateRecord
import kotlinx.serialization.Serializable

@Serializable
data class ThreadGateView(
    val uri: String,
    val cid: String,
    val record: ThreadGateRecord,
    val lists: List<ListViewBasic>
)