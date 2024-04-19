package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = LabelerType.Companion.Serializer::class)
enum class LabelerType(override val identifier: String) : SerializableEnum {
    LabelerView("app.bsky.labeler.defs#labelerView"),
    LabelerViewDetailed("app.bsky.labeler.defs#labelerViewDetailed"),
    Unknown("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<LabelerType>(LabelerType::class)
    }
}