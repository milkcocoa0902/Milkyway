package com.milkcocoa.info.milkyway.models.bsky.richtext

import com.milkcocoa.info.milkyway.types.RichTextType
import kotlinx.serialization.Serializable

@Serializable
data class RichTextMention(
    val did: String
) : RichTextFeature() {
    override val type: RichTextType
        get() = RichTextType.RichTextTypeMention
}