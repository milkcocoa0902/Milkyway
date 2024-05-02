package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val accessJwt: String = "",
        val did: Did,
        val password: String,
        val token: String
    ) : RequireUserSession, AtProtocolPostRequestModel
}