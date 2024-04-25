package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
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
        val did: String,
        val password: String
    ) : AtProtocolRequestWithAdmin
}