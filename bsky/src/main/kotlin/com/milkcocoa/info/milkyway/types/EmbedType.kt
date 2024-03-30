package com.milkcocoa.info.milkyway.types

import kotlinx.serialization.Serializable

@Serializable(with = EmbedTypeSerializer::class)
enum class EmbedType(val identifier: String) {
    EmbedExternal("app.bsky.embed.external"),
    EmbedImages("app.bsky.embed.images"),
    EmbedRecord("app.bsky.embed.record"),
    EmbedRecordWithMedia("app.bsky.embed.recordWithMedia"),
    UnknownEmbed("unknown");

    companion object{
        fun getByIdentifier(identifier: String?) =
            entries.find { it.identifier == identifier } ?: UnknownEmbed
    }
}

