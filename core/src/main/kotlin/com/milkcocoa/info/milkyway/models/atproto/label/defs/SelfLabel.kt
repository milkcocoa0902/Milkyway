package com.milkcocoa.info.milkyway.models.atproto.label.defs

import kotlinx.serialization.Serializable

/**
 * Metadata tag on an atproto record, published by the author within the record. Note that schemas should use #selfLabels, not #selfLabel.
 */
@Serializable
data class SelfLabel(
    /**
     * The short string name of the value or type of this label.
     */
    val `val`: String
)
