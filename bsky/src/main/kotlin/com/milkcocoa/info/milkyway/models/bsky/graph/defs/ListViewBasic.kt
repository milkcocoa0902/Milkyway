package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ListViewBasic(
    val uri: String,
    val cid: String,
    val name: String,
    val purpose: ListPurpose,
    val avatar: String = "",
    val labels: List<Label> = emptyList(),
    val viewer: ListViewerState? = null,
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime? = null
)