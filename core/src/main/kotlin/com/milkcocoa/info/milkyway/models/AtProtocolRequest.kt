package com.milkcocoa.info.milkyway.models

import kotlinx.serialization.Transient

interface AtProtocolRequest

interface AtProtocolRequestWithSession : AtProtocolRequest {
    @Transient
    val accessJwt: String
}