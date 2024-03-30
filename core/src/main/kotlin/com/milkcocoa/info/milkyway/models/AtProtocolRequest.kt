package com.milkcocoa.info.milkyway.models

import com.milkcocoa.info.milkyway.api.atproto.AtProtocol

interface AtProtocolRequest{
    fun toMap() : Map<String, Any> = emptyMap()
}

interface AtProtocolRequestWithSession: AtProtocolRequest{
    val accessJwt: String
}