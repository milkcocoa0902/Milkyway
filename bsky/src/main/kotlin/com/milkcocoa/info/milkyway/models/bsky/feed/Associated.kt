package com.milkcocoa.info.milkyway.models.bsky.feed

import kotlinx.serialization.Serializable

@Serializable
data class Associated(
    val lists: Int? = null,
    val feedgens: Int? = null,
    val labeler: Boolean? = null
)