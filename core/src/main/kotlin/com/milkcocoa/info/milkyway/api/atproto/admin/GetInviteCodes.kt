package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.atproto.server.defs.InviteCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Get details about an account.
 */
class GetInviteCodes(val domain: Domain) :
    AtProtocolGet<GetInviteCodes.GetInviteCodesRequest, GetInviteCodes.GetInviteCodesResponse>(
        AtProtoActions.AdminGetInviteCodes,
        domain,
        GetInviteCodesRequest::class,
        GetInviteCodesResponse::class
    ) {
    @Serializable
    data class GetInviteCodesRequest(
        @Transient
        override val adminPassword: String = "",
        val sort: InviteCodeSortOrder = InviteCodeSortOrder.Recent,
        val limit: Int = 100,
        val cursor: String? = null
    ) : RequireAdminSession, AtProtocolGetRequestModel {
        @Serializable
        enum class InviteCodeSortOrder(val value: String) {
            @SerialName("recent")
            Recent("recent"),

            @SerialName("usage")
            Usage("usage")
        }
    }

    @Serializable
    data class GetInviteCodesResponse(
        val codes: List<InviteCode>
    ) : AtProtocolModel
}