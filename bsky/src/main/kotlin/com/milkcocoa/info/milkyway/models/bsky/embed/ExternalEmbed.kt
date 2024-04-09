package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.External
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEmbed(
    val external: External
) : Embed() {
    override val type: EmbedType
        get() = EmbedType.EmbedExternal
}