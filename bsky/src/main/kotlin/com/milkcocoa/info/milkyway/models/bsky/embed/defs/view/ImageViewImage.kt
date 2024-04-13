package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import kotlinx.serialization.Serializable

@Serializable
data class ImageViewImage(
    val thumb: String,
    val fullsize: String,
    val alt: String,
    val aspectRatio: AspectRatio? = null
)