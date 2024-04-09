package com.milkcocoa.info.milkyway.models.bsky.feed

import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.models.bsky.textdecor.Facet
import com.milkcocoa.info.milkyway.models.entity.Label
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
    val indexedAt: LocalDateTime,
) {
    companion object {
        object IndexedAtSerializer : DateTimeSerializer("indexedAt")
    }
}