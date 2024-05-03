package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.Serializable

@Serializable
data class ImageView(
    val images: List<ImageViewImage>
) : EmbedView() {
    override val type: EmbedViewType
        get() = EmbedViewType.EmbedImagesView
}