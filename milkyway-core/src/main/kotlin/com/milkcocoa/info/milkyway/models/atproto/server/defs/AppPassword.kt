package com.milkcocoa.info.milkyway.models.atproto.server.defs

import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class AppPassword(
    val name: String,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime
)