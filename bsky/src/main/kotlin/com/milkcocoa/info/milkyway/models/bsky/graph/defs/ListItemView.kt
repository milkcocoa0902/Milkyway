package com.milkcocoa.info.milkyway.models.bsky.graph.defs

import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable

@Serializable
data class ListItemView(
    val uri: AtUri,
    val subject: ProfileView
)