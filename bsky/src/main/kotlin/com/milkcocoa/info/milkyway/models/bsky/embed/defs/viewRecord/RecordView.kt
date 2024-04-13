package com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord

import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.EmbedView
import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.Serializable

@Serializable
data class RecordView(
    val record: RecordViewRecord
) : EmbedView() {
    override val type: EmbedViewType
        get() = EmbedViewType.EmbedRecordView
}