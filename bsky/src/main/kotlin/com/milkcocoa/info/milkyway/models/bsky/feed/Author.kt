package com.milkcocoa.info.milkyway.models.bsky.feed

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val did: String,
    val handle: String,
    val displayName: String?,
    val avatar: String?,
    val associated: Associated? = null
//    val viewer: String,
//    val labels: String,
)