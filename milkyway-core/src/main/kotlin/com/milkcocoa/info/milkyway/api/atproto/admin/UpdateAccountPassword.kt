package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Update the password for a user account as an administrator.
 */
class UpdateAccountPassword(val domain: Domain) :
    AtProtocolUnitPost<UpdateAccountPassword.UpdateAccountPasswordRequest>(
        AtProtoActions.AdminUpdateAccountPassword,
        domain,
        UpdateAccountPasswordRequest::class
    ) {
    @Serializable
    data class UpdateAccountPasswordRequest(
        @Transient
        override val adminPassword: String = "",
        val did: Did,
        val password: String
    ) : RequireAdminSession, AtProtocolPostRequestModel
}