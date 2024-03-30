package com.milkcocoa.info.milkyway.types

import kotlinx.serialization.Serializable

@Serializable(with = EmbedViewTypeSerializer::class)
enum class RecordEmbedViewRecordType(val identifier: String){
    EmbedExternalView("app.bsky.embed.external#view"),
    EmbedImagesView("app.bsky.embed.images#view"),
    EmbedRecordView("app.bsky.embed.record#view"),
    EmbedRecordWithMediaView("app.bsky.embed.recordWithMedia#view"),
    UnknownEmbed("unknown");

    companion object{
        fun getByIdentifier(identifier: String?) =
            entries.find { it.identifier == identifier } ?: UnknownEmbed
    }
}

