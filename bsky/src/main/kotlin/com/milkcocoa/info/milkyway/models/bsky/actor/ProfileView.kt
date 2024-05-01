package com.milkcocoa.info.milkyway.models.bsky.actor

import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * post author
 */

@Serializable
data class ProfileView(
    val did: Did,
    val handle: Handle,
    val displayName: String? = null,
    val description: String? = null,
    val avatar: String? = null,
    val associated: Associated? = null,
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime? = null,
    val viewer: ViewerState? = null,
    val labels: List<Label> = emptyList()
)