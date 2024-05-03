package com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord

import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.BlockedAuthor
import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import kotlinx.serialization.Serializable

// record.view#view.viewRecordR
@Serializable
class RecordViewViewBlocked(
    val uri: AtUri,
    val blocked: Boolean,
    val author: BlockedAuthor
) : RecordViewRecord() {
    override val type: EmbedViewRecordType
        get() = EmbedViewRecordType.ViewBlocked
}