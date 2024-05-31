package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

/**
 * Deactivates a currently active account.
 * Stops serving of repo, and future writes to repo until reactivated.
 * Used to finalize account migration with the old host after the account has been activated on the new host.
 */
class DeactivateAccount(val domain: Domain) :
    AtProtocolUnitPost<DeactivateAccount.DeactivateAccountRequest>(
        AtProtoActions.DeactivateAccount,
        domain,
        DeactivateAccountRequest::class
    ) {
    @Serializable
    data class DeactivateAccountRequest(
        @Transient
        override val accessJwt: String = "",
        @Serializable(with = DateTimeSerializer::class)
        val deleteAfter: LocalDateTime
    ) : RequireUserSession, AtProtocolPostRequestModel
}