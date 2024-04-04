package com.milkcocoa.info.milkyway.models

import kotlinx.serialization.Transient

interface AtProtocolRequest {
    fun toMap(): Map<String, Any> = emptyMap()
}

interface AtProtocolRequestWithSession : AtProtocolRequest {
    @Transient
    val accessJwt: String
}