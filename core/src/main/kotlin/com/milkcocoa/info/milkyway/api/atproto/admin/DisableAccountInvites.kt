package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
import kotlinx.serialization.Serializable

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
        override val adminPassword: String,
        val account: String,
        /**
         * Optional reason for disabled invites.
         */
        val note: String? = null
    ) : AtProtocolRequestWithAdmin
}