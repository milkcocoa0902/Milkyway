package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import kotlinx.serialization.Serializable

@Serializable
data class AspectRatio(
    val width: Int,
    val height: Int
)