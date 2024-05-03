package com.milkcocoa.info.milkyway.models.bsky.actor

import kotlinx.serialization.Serializable

@Serializable
data class Associated(
    val lists: Int? = null,
    val feedgens: Int? = null,
    val labeler: Boolean? = null
)