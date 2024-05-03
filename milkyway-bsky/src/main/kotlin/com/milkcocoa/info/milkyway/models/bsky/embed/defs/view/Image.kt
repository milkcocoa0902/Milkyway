package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import com.milkcocoa.info.milkyway.models.entity.BlobBase
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val alt: String,
    val aspectRatio: AspectRatio? = null,
    val image: BlobBase
)