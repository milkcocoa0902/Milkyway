package com.milkcocoa.info.milkyway.models.atproto.server.defs

import kotlinx.serialization.Serializable

@Serializable
data class AccountCodes(
    val account: String,
    val codes: List<String>
)