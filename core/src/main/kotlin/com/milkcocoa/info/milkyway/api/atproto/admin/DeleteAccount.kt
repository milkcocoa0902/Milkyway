package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable

/**
 * Delete a user account as an administrator.
 */
class DeleteAccount(val domain: Domain) :
    AtProtocolUnitPost<DeleteAccount.DeleteAccountRequest>(
        AtProtoActions.AdminDeleteAccount,
        domain,
        DeleteAccountRequest::class
    ) {
    @Serializable
    data class DeleteAccountRequest(
        override val adminPassword: String,
        val did: Did
    ) : RequireAdminSession, AtProtocolPostRequestModel
}