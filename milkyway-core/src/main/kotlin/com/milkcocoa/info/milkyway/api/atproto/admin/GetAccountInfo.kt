package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.atproto.admin.def.AccountView
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val adminPassword: String = "",
        val did: Did
    ) : RequireAdminSession, AtProtocolGetRequestModel
}