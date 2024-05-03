package com.milkcocoa.info.milkyway.models.atproto.server.defs

import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class InviteCode(
    val code: String,
    val available: Boolean,
    val disabled: Boolean,
    val forAccount: Boolean,
    val createdBy: String,
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime,
    val uses: List<InviteCodeUse>
)