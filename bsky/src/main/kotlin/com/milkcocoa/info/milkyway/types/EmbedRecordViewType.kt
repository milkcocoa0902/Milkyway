package com.milkcocoa.info.milkyway.types

import kotlinx.serialization.Serializable

@Serializable(with = EmbedRecordViewTypeSerializer::class)
enum class EmbedRecordViewType(val identifier: String) {
    ViewRecord("app.bsky.embed.record.viewRecord#viewRecord"),
    UnknownEmbed("unknown");

    companion object{
        fun getByIdentifier(identifier: String?) =
            entries.find { it.identifier == identifier } ?: UnknownEmbed
    }
}

