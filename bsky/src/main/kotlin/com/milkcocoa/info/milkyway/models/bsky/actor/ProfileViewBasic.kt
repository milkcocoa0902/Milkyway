package com.milkcocoa.info.milkyway.models.bsky.actor

import com.milkcocoa.info.milkyway.models.entity.Label
import kotlinx.serialization.Serializable

/**
 * post author
 */

@Serializable
data class ProfileViewBasic(
    val did: String,
    val handle: String,
    val displayName: String? = null,
    val avatar: String? = null,
    val associated: Associated? = null,
    val viewer: Viewer? = null,
    val labels: List<Label> = emptyList()
)