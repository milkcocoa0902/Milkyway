package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.atproto.repo.StringRef
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("app.bsky.embed.record")
data class RecordEmbed(
    val record: StringRef
) : Embed() {
    @SerialName("\$type")
    override val type: EmbedType
        get() = EmbedType.EmbedRecord
}