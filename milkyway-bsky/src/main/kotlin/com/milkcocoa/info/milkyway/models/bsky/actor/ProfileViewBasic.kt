package com.milkcocoa.info.milkyway.models.bsky.actor

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
import kotlinx.serialization.Serializable

/**
 * post author
 */

@Serializable
data class ProfileViewBasic(
    val did: Did,
    val handle: Handle,
    val displayName: String? = null,
    val avatar: String? = null,
    val associated: Associated? = null,
    val viewer: ViewerState? = null,
    val labels: List<Label> = emptyList()
)