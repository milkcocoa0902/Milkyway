package com.milkcocoa.info.milkyway.models.bsky.labeler.defs

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.models.bsky.labeler.Labeler
import com.milkcocoa.info.milkyway.types.LabelerType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class LabelerView(
    val uri: String,
    val cid: String,
    val creator: ProfileView,
    val likeCount: Int? = null,
    val viewer: LabelerViewerState? = null,
    @Serializable(with = IndexedAtSerializer::class)
    val indexedAt: LocalDateTime,
    val labels: List<Label>
) : Labeler() {
    override val type: LabelerType
        get() = LabelerType.LabelerView

    companion object {
        object IndexedAtSerializer : DateTimeSerializer("indexedAt")
    }
}