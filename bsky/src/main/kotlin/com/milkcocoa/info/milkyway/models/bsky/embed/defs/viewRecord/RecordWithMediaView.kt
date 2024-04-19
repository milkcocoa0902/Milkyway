package com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord

import com.milkcocoa.info.milkyway.models.bsky.embed.defs.view.EmbedView
import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.Serializable

@Serializable
data class RecordWithMediaView(
    val record: RecordWrap,
    val media: EmbedView
) : EmbedView() {
    @Serializable
    data class RecordWrap(
        val record: RecordViewRecord
    )

    override val type: EmbedViewType
        get() = EmbedViewType.EmbedRecordWithMediaView
}