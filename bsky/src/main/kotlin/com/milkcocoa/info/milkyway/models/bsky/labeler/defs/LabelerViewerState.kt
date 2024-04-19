package com.milkcocoa.info.milkyway.models.bsky.labeler.defs

import kotlinx.serialization.Serializable

@Serializable
data class LabelerViewerState(
    val like: String? = null
)