package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.atproto.server.defs.InviteCode
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Get all invite codes for the current account. Requires auth.
 */
class GetAccountInviteCodes(val domain: Domain) :
    AtProtocolGet<
        GetAccountInviteCodes.GetAccountInviteCodesRequest,
        GetAccountInviteCodes.GetAccountInviteCodesResponse
    >(
        AtProtoActions.GetAccountInviteCodes,
        domain,
        GetAccountInviteCodesRequest::class,
        GetAccountInviteCodesResponse::class
    ) {
    @Serializable
    data class GetAccountInviteCodesRequest(
        @Transient
        override val accessJwt: String = "",
        val includeUsed: Boolean = true,
        /**
         * Controls whether any new 'earned' but not 'created' invites should be created.
         */
        val createAvailable: Boolean = true
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetAccountInviteCodesResponse(
        val codes: List<InviteCode>
    ) : AtProtocolModel
}