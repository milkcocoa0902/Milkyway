package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Activates a currently deactivated account.
 * Used to finalize account migration after the account's repo is imported and identity is setup.
 */
class ActivateAccount(val domain: Domain) :
    AtProtocolUnitPost<ActivateAccount.ActivateAccountRequest>(
        AtProtoActions.ActivateAccount,
        domain,
        ActivateAccountRequest::class
    ) {
    @Serializable
    data class ActivateAccountRequest(
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolPostRequestModel
}