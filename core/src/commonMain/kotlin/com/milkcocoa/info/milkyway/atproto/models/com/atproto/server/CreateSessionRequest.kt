package com.milkcocoa.info.milkyway.atproto.models.com.atproto.server

import com.milkcocoa.info.milkyway.atproto.models.AtProtocolRequest
import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionRequest(
    val identifier: String,
    val password: String
): AtProtocolRequest