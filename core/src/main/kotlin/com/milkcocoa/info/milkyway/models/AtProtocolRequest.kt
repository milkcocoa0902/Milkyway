package com.milkcocoa.info.milkyway.models

import com.milkcocoa.info.milkyway.api.atproto.AtProtocol
import kotlinx.serialization.Transient

interface AtProtocolRequest{
    fun toMap() : Map<String, Any> = emptyMap()
}

interface AtProtocolRequestWithSession: AtProtocolRequest{
    @Transient
    val accessJwt: String
}