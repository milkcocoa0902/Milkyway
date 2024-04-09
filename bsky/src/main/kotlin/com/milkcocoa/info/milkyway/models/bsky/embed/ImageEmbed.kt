package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.Image
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.Serializable

@Serializable
data class ImageEmbed(
    val images: List<Image>
) : Embed() {
    override val type: EmbedType
        get() = EmbedType.EmbedImages
}