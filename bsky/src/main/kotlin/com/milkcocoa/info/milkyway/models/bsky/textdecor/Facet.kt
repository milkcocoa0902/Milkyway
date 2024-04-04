package com.milkcocoa.info.milkyway.models.bsky.textdecor

import kotlinx.serialization.Serializable

@Serializable
data class Facet(
    val index: RichTextRange,
    val features: List<RichTextFeature>
)