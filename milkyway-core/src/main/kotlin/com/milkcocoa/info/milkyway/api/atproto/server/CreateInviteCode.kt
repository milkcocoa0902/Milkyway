package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val adminPassword: String = "",
        val useCount: Int,
        val forAccount: String? = null
    ) : RequireAdminSession, AtProtocolPostRequestModel

    @Serializable
    data class CreateInviteCodeResponse(
        val code: String
    ) : AtProtocolModel
}