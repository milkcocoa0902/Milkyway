package com.milkcocoa.info.milkyway.models.bsky.richtext

import kotlinx.serialization.Serializable

@Serializable
data class RichTextRange(
    val byteStart: Int,
    val byteEnd: Int
)