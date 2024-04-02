package com.milkcocoa.info.milkyway.models.bsky.textdecor

import kotlinx.serialization.Serializable

@Serializable
data class RichTextRange(
    val byteStart: Int,
    val byteEnd: Int
)
