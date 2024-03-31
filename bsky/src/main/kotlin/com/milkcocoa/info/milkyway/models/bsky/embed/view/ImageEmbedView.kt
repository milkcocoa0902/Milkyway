package com.milkcocoa.info.milkyway.models.bsky.embed.view

import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.Serializable

@Serializable
data class ImageEmbedView(
    val images: List<Image>
): EmbedView() {
    override val type: EmbedViewType
        get() = EmbedViewType.EmbedImagesView

    @Serializable
    data class Image(
        val thumb: String,
        val fullsize: String,
        val alt: String,
        val aspectRatio: AspectRatio? = null
    ){
        @Serializable
        data class AspectRatio(
            val width: Int,
            val height: Int
        )
    }
}