package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.entity.Blob
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.Serializable

@Serializable
data class ImageEmbed(
    val images: List<Image>
) : Embed() {
    override val type: EmbedType
        get() = EmbedType.EmbedImages

    @Serializable
    data class Image(
        val alt: String,
        val aspectRatio: AspectRatio,
        val image: Blob
    ) {
        @Serializable
        data class AspectRatio(
            val width: Int,
            val height: Int
        )
    }
}