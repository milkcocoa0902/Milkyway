package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.RecordEmbedViewRecordType
import kotlinx.serialization.Serializable

@Serializable
data class RecordEmbedView(
    val record: RecordEmbedViewRecord
): EmbedView() {
    override val type: RecordEmbedViewRecordType
        get() = RecordEmbedViewRecordType.EmbedRecordView
}