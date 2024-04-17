package com.milkcocoa.info.milkyway.models.bsky.labeler

import com.milkcocoa.info.milkyway.models.bsky.labeler.defs.LabelerViewerState
import com.milkcocoa.info.milkyway.types.LabelerType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class Labeler {
    @SerialName("\$type")
    abstract val type: LabelerType
}