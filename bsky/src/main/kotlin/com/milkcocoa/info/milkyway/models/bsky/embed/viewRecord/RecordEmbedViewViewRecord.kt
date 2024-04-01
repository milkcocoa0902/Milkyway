package com.milkcocoa.info.milkyway.models.bsky.embed.viewRecord

import com.milkcocoa.info.milkyway.models.bsky.embed.view.EmbedView
import com.milkcocoa.info.milkyway.models.bsky.actor.ActorProfileView
import com.milkcocoa.info.milkyway.models.entity.Label
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import kotlinx.serialization.Serializable

// record.view#view.viewRecordR
@Serializable
class RecordEmbedViewViewRecord(
    val uri: String,
    val cid: String,
    val author: ActorProfileView,
    val value: BskyRecord,
    val labels: List<Label> = emptyList(),
    val embeds: List<EmbedView> = emptyList(),
    val indexedAt: String


): RecordEmbedViewRecord(){
    override val type: EmbedViewRecordType
        get() = EmbedViewRecordType.ViewRecord
}