package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.Image
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("app.bsky.embed.images")
data class ImageEmbed(
    val images: List<Image>
) : Embed() {
    @SerialName("\$type")
    override val type: EmbedType
        get() = EmbedType.EmbedImages
}