package com.milkcocoa.info.milkyway.models.bsky.textdecor

import com.milkcocoa.info.milkyway.types.RichTextType
import kotlinx.serialization.Serializable

@Serializable
data class RichTextTag(
    val tag: String
) : RichTextFeature() {
    override val type: RichTextType
        get() = RichTextType.RichTextTypeTag
}