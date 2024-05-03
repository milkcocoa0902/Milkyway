package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = EmbedViewType.Companion.Serializer::class)
enum class EmbedViewType(override val identifier: String) : SerializableEnum {
    EmbedExternalView("app.bsky.embed.external#view"),
    EmbedImagesView("app.bsky.embed.images#view"),
    EmbedRecordView("app.bsky.embed.record#view"),
    EmbedRecordWithMediaView("app.bsky.embed.recordWithMedia#view"),
    UnknownEmbed("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: UnknownEmbed

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<EmbedViewType>(EmbedViewType::class)
    }
}