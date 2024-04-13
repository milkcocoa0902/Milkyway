package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.Serializable

@Serializable
data class RecordWithMediaEmbed(
    val media: Embed,
    val record: RecordEmbed
) : Embed() {
    override val type: EmbedType
        get() = EmbedType.EmbedRecordWithMedia
}