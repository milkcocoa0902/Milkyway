package com.milkcocoa.info.milkyway.atproto.models.com.atproto.repo

import com.milkcocoa.info.milkyway.atproto.models.AtProtocolModel
import kotlinx.serialization.Serializable

@Serializable
data class CreateRecordResponse(
    val uri: String,
    val cid: String
): AtProtocolModel