package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Disable an account from receiving new invite codes, but does not invalidate existing codes.
 */
class DisableAccountInvites(val domain: Domain) :
    AtProtocolUnitPost<DisableAccountInvites.DisableAccountInvitesRequest>(
        AtProtoActions.AdminDisableAccountInvites,
        domain,
        DisableAccountInvitesRequest::class
    ) {
    @Serializable
    data class DisableAccountInvitesRequest(
        @Transient
        override val adminPassword: String = "",
        val account: Did,
        /**
         * Optional reason for disabled invites.
         */
        val note: String? = null
    ) : RequireAdminSession, AtProtocolPostRequestModel
}