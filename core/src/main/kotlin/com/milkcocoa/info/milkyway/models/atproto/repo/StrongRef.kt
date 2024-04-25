package com.milkcocoa.info.milkyway.models.atproto.repo

import com.milkcocoa.info.milkyway.ReferenceType
import com.milkcocoa.info.milkyway.models.Reference
import kotlinx.serialization.Serializable

@Serializable
data class StrongRef(
    val cid: String,
    val uri: String
) : Reference() {
    override val type: ReferenceType
        get() = ReferenceType.StrongRef
}