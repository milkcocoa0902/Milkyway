package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable

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
        override val adminPassword: String,
        val did: Did,
        val password: String
    ) : RequireAdminSession, AtProtocolPostRequestModel
}