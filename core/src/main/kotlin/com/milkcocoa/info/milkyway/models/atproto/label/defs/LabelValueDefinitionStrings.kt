package com.milkcocoa.info.milkyway.models.atproto.label.defs

import kotlinx.serialization.Serializable

/**
 * Strings which describe the label in the UI, localized into a specific language.
 */
@Serializable
data class LabelValueDefinitionStrings(
    /**
     * The code of the language these strings are written in.
     */
    val lang: String,
    /**
     * A short human-readable name for the label.
     */
    val name: String,
    /**
     * A longer description of what the label means and why it might be applied.
     */
    val description: String,
)
