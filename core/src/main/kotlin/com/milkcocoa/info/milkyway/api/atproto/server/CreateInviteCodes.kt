package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.atproto.server.defs.AccountCodes
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 *Create invite codes.
 */
class CreateInviteCodes(val domain: Domain) :
    AtProtocolPost<CreateInviteCodes.CreateInvitesCodeRequest, CreateInviteCodes.CreateInviteCodesResponse>(
        AtProtoActions.CreateInviteCodes,
        domain,
        CreateInvitesCodeRequest::class,
        CreateInviteCodesResponse::class
    ) {
    @Serializable
    data class CreateInvitesCodeRequest(
        @Transient
        override val adminPassword: String = "",
        val codeCount: Int = 1,
        val useCount: Int,
        val forAccounts: List<String> = emptyList()
    ) : RequireAdminSession, AtProtocolPostRequestModel

    @Serializable
    data class CreateInviteCodesResponse(
        val codes: List<AccountCodes>
    ) : AtProtocolModel
}