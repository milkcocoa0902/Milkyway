package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

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
        override val accessJwt: String
    ) : AtProtocolRequestWithSession
}