package com.milkcocoa.info.milkyway.atproto.models.did

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val id: String,
    val type: String,
    val serviceEndpoint: String
)
