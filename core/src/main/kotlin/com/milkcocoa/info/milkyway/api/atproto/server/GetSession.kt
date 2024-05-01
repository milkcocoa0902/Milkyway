package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
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
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetSessionResponse(
        val handle: Handle,
        val did: Did,
        val email: String? = null,
        val emailConfirmed: Boolean? = null,
        val emailAuthFactor: Boolean? = null,
        val didDoc: DidDoc? = null
    ) : AtProtocolModel
}