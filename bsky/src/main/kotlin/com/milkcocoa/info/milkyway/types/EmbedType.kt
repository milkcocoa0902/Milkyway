package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = EmbedType.Companion.Serializer::class)
enum class EmbedType(override val identifier: String) : SerializableEnum{
    EmbedExternal("app.bsky.embed.external"),
    EmbedImages("app.bsky.embed.images"),
    EmbedRecord("app.bsky.embed.record"),
    EmbedRecordWithMedia("app.bsky.embed.recordWithMedia"),
    UnknownEmbed("unknown");

    companion object{
        fun getByIdentifier(identifier: String?) =
            entries.find { it.identifier == identifier } ?: UnknownEmbed


        object Serializer: SerializableEnum.Companion.SerializableEnumSerializer<EmbedType>(EmbedType::class)
    }
}

