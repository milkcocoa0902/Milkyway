package com.milkcocoa.info.milkyway.models.atproto.admin.def

import kotlinx.serialization.Serializable

@Serializable
data class StatusAttr(
    val applied: Boolean,
    val ref: String = ""
)