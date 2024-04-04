package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.Serializable

@Serializable
data class RecordEmbed(
    val record: RecordRef
) : Embed() {
    override val type: EmbedType
        get() = EmbedType.EmbedRecord

    @Serializable
    data class RecordRef(
        val cid: String,
        val uri: String
    )
}