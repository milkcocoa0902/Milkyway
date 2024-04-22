package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

/**
 * Create an invite code.
 */
class CreateInviteCode(val domain: Domain) :
    AtProtocolPost<CreateInviteCode.CreateInviteCodeRequest, CreateInviteCode.CreateInviteCodeResponse>(
        AtProtoActions.CreateInviteCode,
        domain,
        CreateInviteCodeRequest::class,
        CreateInviteCodeResponse::class
    ) {
    @Serializable
    data class CreateInviteCodeRequest(
        override val adminPassword: String,
        val useCount: Int,
        val forAccount: String? = null
    ) : AtProtocolRequestWithAdmin

    @Serializable
    data class CreateInviteCodeResponse(
        val code: String
    ) : AtProtocolModel
}