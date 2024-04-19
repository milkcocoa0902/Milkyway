package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ListView(
    val uri: String,
    val cid: String,
    val creator: ProfileView,
    val name: String,
    val purpose: ListPurpose,
    val description: String = "",
    val descriptionFacets: List<Facet> = emptyList(),
    val avatar: String = "",
    val labels: List<Label> = emptyList(),
    val viewer: ListViewerState? = null,
    @Serializable(with = IndexedAtSerializer::class)
    val indexedAt: LocalDateTime
) {
    companion object {
        object IndexedAtSerializer : DateTimeSerializer("indexedAt")
    }
}