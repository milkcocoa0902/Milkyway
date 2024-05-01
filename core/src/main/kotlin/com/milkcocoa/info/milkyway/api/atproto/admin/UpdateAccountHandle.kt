package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
import kotlinx.serialization.Serializable

/**
 * Administrative action to update an account's handle.
 */
class UpdateAccountHandle(val domain: Domain) :
    AtProtocolUnitPost<UpdateAccountHandle.UpdateAccountHandleRequest>(
        AtProtoActions.AdminUpdateAccountHandle,
        domain,
        UpdateAccountHandleRequest::class
    ) {
    @Serializable
    data class UpdateAccountHandleRequest(
        override val adminPassword: String,
        val did: Did,
        val handle: Handle
    ) : RequireAdminSession, AtProtocolPostRequestModel
}