package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Re-enable an account's ability to receive invite codes.
 */
class EnableAccountInvites(val domain: Domain) :
    AtProtocolUnitPost<EnableAccountInvites.EnableAccountInvitesRequest>(
        AtProtoActions.AdminEnableAccountInvites,
        domain,
        EnableAccountInvitesRequest::class
    ) {
    @Serializable
    data class EnableAccountInvitesRequest(
        @Transient
        override val adminPassword: String = "",
        val account: Did,
        /**
         * Optional reason for enabled invites.
         */
        val note: String? = null
    ) : RequireAdminSession, AtProtocolPostRequestModel
}