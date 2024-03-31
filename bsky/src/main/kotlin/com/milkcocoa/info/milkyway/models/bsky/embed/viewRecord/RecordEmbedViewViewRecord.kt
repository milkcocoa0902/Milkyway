package com.milkcocoa.info.milkyway.models.bsky.embed.viewRecord

import com.milkcocoa.info.milkyway.models.bsky.feed.Author
import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import kotlinx.serialization.Serializable

// record.view#view.viewRecordR
@Serializable
class RecordEmbedViewViewRecord(
    val uri: String,
    val cid: String,
    val author: Author,
    val value: String,
//    val labels: String,
//    val embeds: String
    val indexedAt: String


): RecordEmbedViewRecord(){
    override val type: EmbedViewRecordType
        get() = EmbedViewRecordType.ViewRecord
}