package com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileViewBasic
import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.EmbedView
import com.milkcocoa.info.milkyway.types.BskyRecordType
import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import kotlinx.serialization.Serializable

// record.view#view.viewRecordR
@Serializable
class RecordViewViewRecord(
    val uri: AtUri,
    val cid: String,
    val author: ProfileViewBasic,
    val value: Record<BskyRecordType>,
    val labels: List<Label> = emptyList(),
    val embeds: List<EmbedView> = emptyList(),
    val indexedAt: String
) : RecordViewRecord() {
    override val type: EmbedViewRecordType
        get() = EmbedViewRecordType.ViewRecord
}