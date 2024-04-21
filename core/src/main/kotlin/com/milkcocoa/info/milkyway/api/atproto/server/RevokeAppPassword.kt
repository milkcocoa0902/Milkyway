package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

/**
 * Revoke an App Password by name.
 */
class RevokeAppPassword(val domain: Domain) :
    AtProtocolUnitPost<RevokeAppPassword.RevokeAppPasswordRequest>(
        AtProtoActions.RevokeAppPassword,
        domain,
        RevokeAppPasswordRequest::class
    ) {
    @Serializable
    data class RevokeAppPasswordRequest(
        override val accessJwt: String,
        val name: String
    ) : AtProtocolRequestWithSession
}