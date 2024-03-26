package com.milkcocoa.info.milkeyway.atproto.models

import com.milkcocoa.info.milkeyway.atproto.models.did.DidDoc
import kotlinx.serialization.Serializable

@Serializable
data class CreateRecord(
    val uri: String,
    val cid: String
): AtProtocolModel