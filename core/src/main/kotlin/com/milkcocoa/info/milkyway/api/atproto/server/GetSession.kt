package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.did.DidDoc
import kotlinx.serialization.Serializable

/**
 * Get information about the current auth session. Requires auth.
 */
class GetSession(val domain: Domain) :
    AtProtocolGet<GetSession.GetSessionRequest, GetSession.GetSessionResponse>(
        AtProtoActions.GetSession,
        domain,
        GetSessionRequest::class,
        GetSessionResponse::class
    ) {
    @Serializable
    data class GetSessionRequest(
        override val accessJwt: String
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetSessionResponse(
        val handle: String,
        val did: String,
        val email: String? = null,
        val emailConfirmed: Boolean? = null,
        val emailAuthFactor: Boolean? = null,
        val didDoc: DidDoc? = null
    ) : AtProtocolModel
}