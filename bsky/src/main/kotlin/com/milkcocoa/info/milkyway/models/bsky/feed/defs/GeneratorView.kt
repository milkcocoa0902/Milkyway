package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.EmbedView
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.types.EmbedViewType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable()
data class GeneratorView(
    val uri: String,
    val cid: String,
    val did: String,
    val creator: ProfileView,
    val displayName: String,
    val description: String = "",
    val descriptionFacets: List<Facet> = emptyList(),
    val avatar: String = "",
    val likeCount: Int? = null,
    val labels: List<Label>? = emptyList(),
    val viewer: ViewerState? = null,
    @Serializable(with = IndexedAtSerializer::class)
    val indexedAt: LocalDateTime
) : EmbedView() {
    override val type: EmbedViewType
        get() = EmbedViewType.GeneratorView

    companion object {
        object IndexedAtSerializer : DateTimeSerializer("indexedAt")
    }
}