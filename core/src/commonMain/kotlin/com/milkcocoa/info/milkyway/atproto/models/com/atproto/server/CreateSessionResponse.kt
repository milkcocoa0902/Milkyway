package com.milkcocoa.info.milkyway.atproto.models.com.atproto.server

import com.milkcocoa.info.milkyway.atproto.models.AtProtocolModel
import com.milkcocoa.info.milkyway.atproto.models.did.DidDoc
import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionResponse(
    val did: String,
    val didDoc: DidDoc,
    val handle: String,
    val email: String,
    val emailConfirmed: Boolean,
    val accessJwt: String,
    val refreshJwt: String
): AtProtocolModel