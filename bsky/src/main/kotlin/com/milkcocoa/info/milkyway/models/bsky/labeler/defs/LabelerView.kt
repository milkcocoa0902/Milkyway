package com.milkcocoa.info.milkyway.models.bsky.labeler.defs

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord.RecordViewRecord
import com.milkcocoa.info.milkyway.models.bsky.labeler.Labeler
import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import com.milkcocoa.info.milkyway.types.LabelerType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class LabelerView(
    val uri: AtUri,
    val cid: String,
    val creator: ProfileView,
    val likeCount: Int? = null,
    val viewer: LabelerViewerState? = null,
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime,
    val labels: List<Label>
) : Labeler() {
    override val type: LabelerType
        get() = LabelerType.LabelerView

    @Serializable
    object LabelerViewAsViewRecord : RecordViewRecord() {
        override val type: EmbedViewRecordType
            get() = EmbedViewRecordType.ViewLabelerView
    }
}