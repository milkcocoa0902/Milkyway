package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("app.bsky.embed.recordWithMedia")
data class RecordWithMediaEmbed(
    val media: Embed,
    val record: RecordEmbed
) : Embed() {
    @SerialName("\$type")
    override val type: EmbedType
        get() = EmbedType.EmbedRecordWithMedia
}