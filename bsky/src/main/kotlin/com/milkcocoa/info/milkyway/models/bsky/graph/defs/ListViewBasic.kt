package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import com.milkcocoa.info.milkyway.models.entity.Label
import kotlinx.serialization.Serializable

@Serializable
data class ListViewBasic(
    val uri: String,
    val cid: String,
    val name: String,
    val purpose: String,
    val avatar: String,
    val labels: List<Label> = emptyList(),
    val viewer: ListViewerState,
    val indexedAt: String
)