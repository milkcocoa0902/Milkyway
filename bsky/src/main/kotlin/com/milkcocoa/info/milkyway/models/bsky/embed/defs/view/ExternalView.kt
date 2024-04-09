package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.Serializable

@Serializable
data class ExternalView(
    val external: ExternalViewExternal
) : EmbedView() {
    override val type: EmbedViewType
        get() = EmbedViewType.EmbedExternalView
}