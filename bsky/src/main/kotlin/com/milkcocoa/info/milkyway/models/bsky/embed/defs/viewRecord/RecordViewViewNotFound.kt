package com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord

import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import kotlinx.serialization.Serializable

@Serializable
class RecordViewViewNotFound(
    val uri: String,
    val notFound: Boolean = true
) : RecordViewRecord() {
    override val type: EmbedViewRecordType
        get() = EmbedViewRecordType.ViewRecord
}