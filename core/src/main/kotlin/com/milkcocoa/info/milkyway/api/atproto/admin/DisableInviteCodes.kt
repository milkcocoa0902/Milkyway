package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Disable some set of codes and/or all codes associated with a set of users.
 */
class DisableInviteCodes(val domain: Domain) :
    AtProtocolUnitPost<DisableInviteCodes.DisableInviteCodesRequest>(
        AtProtoActions.AdminDisableInviteCodes,
        domain,
        DisableInviteCodesRequest::class
    ) {
    @Serializable
    data class DisableInviteCodesRequest(
        @Transient
        override val adminPassword: String = "",
        val codes: List<String> = emptyList(),
        val accounts: List<String> = emptyList()
    ) : RequireAdminSession, AtProtocolPostRequestModel
}