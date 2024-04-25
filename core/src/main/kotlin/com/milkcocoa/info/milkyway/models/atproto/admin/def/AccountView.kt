package com.milkcocoa.info.milkyway.models.atproto.admin.def

import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.atproto.server.defs.InviteCode
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class AccountView(
    val did: String,
    val handle: String,
    val email: String? = null,
    val relatedRecords: List<Record<*>> = emptyList(),
    @Serializable(with = DateTimeSerializer::class)
    val indexedAt: LocalDateTime,
    val invitedBy: InviteCode? = null,
    val invites: List<InviteCode> = emptyList(),
    val invitedDisabled: Boolean = false,
    @Serializable(with = DateTimeSerializer::class)
    val emailConfirmedAt: LocalDateTime? = null,
    val inviteNote: String? = null
) : AtProtocolModel