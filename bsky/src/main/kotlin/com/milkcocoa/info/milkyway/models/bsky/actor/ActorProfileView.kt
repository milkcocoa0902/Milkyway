package com.milkcocoa.info.milkyway.models.bsky.actor

import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

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
    val labels: List<String> = emptyList(),
)