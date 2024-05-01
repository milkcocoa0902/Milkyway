package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.atproto.admin.def.AccountView
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable

/**
 * Get details about an account.
 */
class GetAccountInfo(val domain: Domain) :
    AtProtocolGet<GetAccountInfo.GetAccountInfoRequest, AccountView>(
        AtProtoActions.AdminGetAccountInfo,
        domain,
        GetAccountInfoRequest::class,
        AccountView::class
    ) {
    @Serializable
    data class GetAccountInfoRequest(
        override val adminPassword: String,
        val did: Did
    ) : RequireAdminSession, AtProtocolGetRequestModel
}