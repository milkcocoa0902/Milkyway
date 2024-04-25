package com.milkcocoa.info.milkyway.models.atproto.admin.def

import com.milkcocoa.info.milkyway.ReferenceType
import com.milkcocoa.info.milkyway.models.Reference
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable

@Serializable
data class RepoBlobRef(
    val did: String,
    val cid: String,
    val recordUri: AtUri
) : Reference() {
    override val type: ReferenceType
        get() = ReferenceType.RepoBlobRef
}