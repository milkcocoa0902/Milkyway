package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import com.milkcocoa.info.milkyway.models.entity.Blob
import kotlinx.serialization.Serializable

@Serializable
data class External(
    val uri: String,
    val title: String,
    val description: String,
    val thumb: Blob? = null
)