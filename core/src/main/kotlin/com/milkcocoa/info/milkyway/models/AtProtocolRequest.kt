package com.milkcocoa.info.milkyway.models

import kotlinx.serialization.Transient

interface AtProtocolRequest

interface RequireAdminSession : AtProtocolRequest {
    @Transient
    val adminPassword: String
}

interface RequireUserSession : AtProtocolRequest {
    @Transient
    val accessJwt: String
}

interface AtProtocolGetRequestModel : AtProtocolRequest

interface AtProtocolPostRequestModel : AtProtocolRequest

interface AtProtocolBlobPostRequestModel : AtProtocolPostRequestModel {
    val binary: ByteArray
}