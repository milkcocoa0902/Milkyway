package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.atproto.repo.StringRef
import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.Serializable

@Serializable
data class RecordEmbed(
    val record: StringRef
) : Embed() {
    override val type: EmbedType
        get() = EmbedType.EmbedRecord
}