package com.milkcocoa.info.milkyway.models.bsky.actor

import kotlinx.serialization.Serializable

/**
 * post author
 */

@Serializable
data class ActorProfileView(
    val did: String,
    val handle: String,
    val displayName: String? = null,
    val avatar: String? = null,
    val associated: Associated? = null,
    val viewer: Viewer? = null,
    val labels: List<String> = emptyList()
)