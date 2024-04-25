package com.milkcocoa.info.milkyway.models.bsky.actor

import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.atproto.label.defs.Label
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * get profile
 */

@Serializable
data class ActorProfileViewDetails(
    val did: String,
    val handle: String,
    val displayName: String? = null,
    val description: String? = null,
    val avatar: String? = null,
    val banner: String? = null,
    val followersCount: Int? = null,
    val followsCount: Int? = null,
    val postsCount: Int? = null,
    val associated: Associated? = null,
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime? = null,
    val viewer: ViewerState? = null,
    val labels: List<Label> = emptyList()
) : AtProtocolModel