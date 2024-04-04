package com.milkcocoa.info.milkyway.models.bsky.embed.viewRecord

import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import kotlinx.serialization.Serializable

@Serializable
class RecordEmbedViewNotFound(
    val uri: String,
    val notFound: Boolean = true
) : RecordEmbedViewRecord() {
    override val type: EmbedViewRecordType
        get() = EmbedViewRecordType.ViewRecord
}