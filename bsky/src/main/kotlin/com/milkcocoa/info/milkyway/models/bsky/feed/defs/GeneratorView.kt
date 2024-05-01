package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord.RecordViewRecord
import com.milkcocoa.info.milkyway.models.bsky.richtext.Facet
import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable()
data class GeneratorView(
    val uri: AtUri,
    val cid: String,
    val did: Did,
    val creator: ProfileView,
    val displayName: String,
    val description: String = "",
    val descriptionFacets: List<Facet> = emptyList(),
    val avatar: String = "",
    val likeCount: Int? = null,
    val labels: List<Label>? = emptyList(),
    val viewer: ViewerState? = null,
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime
) {
    @Serializable
    object GeneratorViewAsViewRecord : RecordViewRecord() {
        override val type: EmbedViewRecordType
            get() = EmbedViewRecordType.ViewGeneratorView
    }
}