package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
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
        val did: String,
        val handle: String
    ) : AtProtocolRequestWithAdmin
}