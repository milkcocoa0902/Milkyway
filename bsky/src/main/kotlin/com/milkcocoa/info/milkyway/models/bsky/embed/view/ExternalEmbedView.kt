package com.milkcocoa.info.milkyway.models.bsky.embed.view

import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEmbedView(
    val external: External
) : EmbedView() {
    override val type: EmbedViewType
        get() = EmbedViewType.EmbedExternalView

    @Serializable
    data class External(
        val uri: String,
        val title: String,
        val description: String,
        val thumb: String? = null
    )
}