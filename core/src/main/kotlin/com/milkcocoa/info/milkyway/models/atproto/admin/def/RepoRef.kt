package com.milkcocoa.info.milkyway.models.atproto.admin.def

import com.milkcocoa.info.milkyway.ReferenceType
import com.milkcocoa.info.milkyway.models.Reference
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable

@Serializable
data class RepoRef(
    val did: Did
) : Reference() {
    override val type: ReferenceType
        get() = ReferenceType.RepoRef
}