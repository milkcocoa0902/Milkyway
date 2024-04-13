package com.milkcocoa.info.milkyway.models.atproto.repo

import kotlinx.serialization.Serializable

@Serializable
data class StringRef(
    val cid: String,
    val uri: String
)