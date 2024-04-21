package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

/**
 * Delete an actor's account with a token and password.
 * Can only be called after requesting a deletion token. Requires auth.
 */
class DeleteAccount(val domain: Domain) :
    AtProtocolUnitPost<DeleteAccount.DeleteAccountRequest>(
        AtProtoActions.DeleteAccount,
        domain,
        DeleteAccountRequest::class
    ) {
    @Serializable
    data class DeleteAccountRequest(
        override val accessJwt: String,
        val did: String,
        val password: String,
        val token: String
    ) : AtProtocolRequestWithSession
}