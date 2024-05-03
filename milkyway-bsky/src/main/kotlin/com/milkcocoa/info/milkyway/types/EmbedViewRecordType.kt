package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = EmbedViewRecordType.Companion.Serializer::class)
enum class EmbedViewRecordType(override val identifier: String) : SerializableEnum {
    ViewRecord("app.bsky.embed.record#viewRecord"),
    ViewNotFound("app.bsky.embed.record#viewNotFound"),
    ViewBlocked("app.bsky.embed.record#viewBlocked"),
    ViewGeneratorView("app.bsky.feed.defs#generatorView"),
    ViewListView("app.bsky.graph.defs#listView"),
    ViewLabelerView("app.bsky.labeler.defs#labelerView"),
    UnknownEmbed("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: UnknownEmbed

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<EmbedViewRecordType>(
            EmbedViewRecordType::class
        )
    }
}