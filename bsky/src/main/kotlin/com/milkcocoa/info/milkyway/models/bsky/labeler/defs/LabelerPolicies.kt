package com.milkcocoa.info.milkyway.models.bsky.labeler.defs

import com.milkcocoa.info.milkyway.models.atproto.label.defs.LabelValue
import com.milkcocoa.info.milkyway.models.atproto.label.defs.LabelValueDefinition
import kotlinx.serialization.Serializable

@Serializable
data class LabelerPolicies(
    /**
     * The label values which this labeler publishes. May include global or custom labels.
     */
    val labelValues: List<LabelValue>,
    /**
     * Label values created by this labeler and scoped exclusively to it. Labels defined here will override global label definitions for this labeler.
     */
    val labelValueDefinitions: List<LabelValueDefinition>
)