package com.milkcocoa.info.milkeyway.atproto.models

import com.milkcocoa.info.milkeyway.atproto.models.did.DidDoc
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val did: String,
    val didDoc: DidDoc,
    val handle: String,
    val email: String,
    val emailConfirmed: Boolean,
    val accessJwt: String,
    val refreshJwt: String
): AtProtocolModel