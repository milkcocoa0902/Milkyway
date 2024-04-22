package com.milkcocoa.info.milkyway.models

import kotlinx.serialization.Transient

interface AtProtocolRequest

interface AtProtocolRequestWithAdmin: AtProtocolRequest{
    @Transient
    val adminPassword: String
}

interface AtProtocolRequestWithSession : AtProtocolRequest {
    @Transient
    val accessJwt: String
}

interface AtProtocolBlobRequestWithSession : AtProtocolRequestWithSession {
    val binary: ByteArray
}