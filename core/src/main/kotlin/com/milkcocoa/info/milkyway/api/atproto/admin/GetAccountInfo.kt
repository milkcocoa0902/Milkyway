package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
import com.milkcocoa.info.milkyway.models.atproto.admin.def.AccountView
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
        val did: String
    ) : AtProtocolRequestWithAdmin
}