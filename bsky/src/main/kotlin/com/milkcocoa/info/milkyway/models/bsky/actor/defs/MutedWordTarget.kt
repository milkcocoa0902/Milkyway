package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import kotlinx.serialization.Serializable

@Serializable
data class MutedWordTarget(
    val value: String,
    val targets: List<String>
)
