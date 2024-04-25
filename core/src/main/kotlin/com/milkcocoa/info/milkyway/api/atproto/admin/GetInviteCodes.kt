package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
import com.milkcocoa.info.milkyway.models.atproto.server.defs.InviteCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Get details about an account.
 */
class GetInviteCodes(val domain: Domain) :
    AtProtocolGet<GetInviteCodes.GetInviteCodesRequest, GetInviteCodes.GetInviteCodesResponse>(
        AtProtoActions.AdminGetAccountInfo,
        domain,
        GetInviteCodesRequest::class,
        GetInviteCodesResponse::class
    ) {
    @Serializable
    data class GetInviteCodesRequest(
        override val adminPassword: String,
        val sort: InviteCodeSortOrder = InviteCodeSortOrder.Recent,
        val limit: Int = 100,
        val cursor: String? = null
    ) : AtProtocolRequestWithAdmin {
        @Serializable
        enum class InviteCodeSortOrder {
            @SerialName("recent")
            Recent,

            @SerialName("usage")
            Usage
        }
    }

    @Serializable
    data class GetInviteCodesResponse(
        val codes: List<InviteCode>
    ) : AtProtocolModel
}