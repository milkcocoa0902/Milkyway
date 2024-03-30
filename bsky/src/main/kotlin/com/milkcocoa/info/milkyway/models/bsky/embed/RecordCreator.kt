package com.milkcocoa.info.milkyway.models.bsky.embed

import kotlinx.serialization.Serializable

@Serializable
data class RecordCreator(
    val did: String,
    val handle: String,
    val displayName: String,
    val description: String,
    val avatar: String,
    //
    //val associated: String,
    val indexedAt: String,
//    val viewer: String,
//    val labels: String,
)
