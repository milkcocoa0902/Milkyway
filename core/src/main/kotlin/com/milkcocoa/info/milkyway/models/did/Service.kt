package com.milkcocoa.info.milkyway.models.did

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val id: String,
    val type: String,
    val serviceEndpoint: String
)
