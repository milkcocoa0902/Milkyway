package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import kotlinx.serialization.Serializable

@Serializable
data class MutedWord(
    val value: String,
    val targets: List<MutedWordTarget>
)