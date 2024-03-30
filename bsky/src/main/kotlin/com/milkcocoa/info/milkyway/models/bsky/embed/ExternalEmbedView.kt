package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.RecordEmbedViewRecordType
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEmbedView(
    val external: External
): EmbedView() {
    override val type: RecordEmbedViewRecordType
        get() = RecordEmbedViewRecordType.EmbedExternalView
    @Serializable
    data class External(
        val uri: String,
        val title: String,
        val description: String,
        val thumb: String? = null
    )
}