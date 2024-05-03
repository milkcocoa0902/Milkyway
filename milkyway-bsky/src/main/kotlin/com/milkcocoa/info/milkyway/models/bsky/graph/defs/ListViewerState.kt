package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import kotlinx.serialization.Serializable

@Serializable
data class ListViewerState(
    val muted: Boolean? = null,
    val blocked: String? = null
)