package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Delete the current session. Requires auth.
 */
class DeleteSession(val domain: Domain) :
    AtProtocolUnitPost<DeleteSession.DeleteSessionRequest>(
        AtProtoActions.DeleteSession,
        domain,
        DeleteSessionRequest::class
    ) {
    @Serializable
    data class DeleteSessionRequest(
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolPostRequestModel
}