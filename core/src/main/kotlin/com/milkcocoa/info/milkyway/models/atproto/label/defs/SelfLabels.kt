package com.milkcocoa.info.milkyway.models.atproto.label.defs

import kotlinx.serialization.Serializable

/**
 * Metadata tags on an atproto record, published by the author within the record.
 */
@Serializable
data class SelfLabels(
    val values: List<SelfLabel>
)
