package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = EmbedViewRecordType.Companion.Serializer::class)
enum class EmbedViewRecordType(override val identifier: String) : SerializableEnum {
    ViewRecord("app.bsky.embed.record#viewRecord"),
    ViewNotFound("app.bsky.embed.record#viewNotFound"),
    UnknownEmbed("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: UnknownEmbed

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<EmbedViewRecordType>(
            EmbedViewRecordType::class
        )
    }
}