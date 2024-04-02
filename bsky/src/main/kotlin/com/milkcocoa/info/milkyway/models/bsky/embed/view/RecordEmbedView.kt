package com.milkcocoa.info.milkyway.models.bsky.embed.view

import com.milkcocoa.info.milkyway.models.bsky.embed.viewRecord.RecordEmbedViewRecord
import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.Serializable

@Serializable
data class RecordEmbedView(
    val record: RecordEmbedViewRecord
): EmbedView() {
    override val type: EmbedViewType
        get() = EmbedViewType.EmbedRecordView
}