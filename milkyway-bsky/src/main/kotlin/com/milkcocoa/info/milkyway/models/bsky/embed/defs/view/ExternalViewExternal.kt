package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import kotlinx.serialization.Serializable

@Serializable
data class ExternalViewExternal(
    val uri: String,
    val title: String,
    val description: String,
    val thumb: String? = null
)