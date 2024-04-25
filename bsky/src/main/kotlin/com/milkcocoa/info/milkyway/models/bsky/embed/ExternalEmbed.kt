package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.External
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("app.bsky.embed.external")
data class ExternalEmbed(
    val external: External
) : Embed() {
    @SerialName("\$type")
    override val type: EmbedType
        get() = EmbedType.EmbedExternal
}