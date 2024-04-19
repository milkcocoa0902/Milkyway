package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = RelationshipType.Companion.Serializer::class)
enum class RelationshipType(override val identifier: String) : SerializableEnum {
    Relationship("app.bsky.graph.defs#relationship"),
    NotFoundActor("app.bsky.graph.defs#notFoundActor"),
    Unknown("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<RelationshipType>(
            RelationshipType::class
        )
    }
}