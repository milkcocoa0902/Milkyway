package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = RichTextType.Companion.Serializer::class)
enum class RichTextType(override val identifier: String) : SerializableEnum {
    RichTextTypeLink("app.bsky.richtext.facet#link"),
    Unknown("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<RichTextType>(RichTextType::class)
    }
}