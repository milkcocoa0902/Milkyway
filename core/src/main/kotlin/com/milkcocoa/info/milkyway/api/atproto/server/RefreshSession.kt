package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
import com.milkcocoa.info.milkyway.models.did.DidDoc
import kotlinx.serialization.Serializable

/**
 * Refresh an authentication session. Requires auth using the 'refreshJwt' (not the 'accessJwt').
 */
class RefreshSession(val domain: Domain) :
    AtProtocolPost<RefreshSession.RefreshSessionRequest, RefreshSession.RefreshSessionResponse>(
        AtProtoActions.RefreshSession,
        domain,
        RefreshSessionRequest::class,
        RefreshSessionResponse::class
    ) {
    @Serializable
    data class RefreshSessionRequest(
        /**
         * refreshToken
         */
        override val accessJwt: String
    ) : RequireUserSession, AtProtocolPostRequestModel

    @Serializable
    data class RefreshSessionResponse(
        val accessJwt: String,
        val refreshJwt: String,
        val handle: Handle,
        val did: Did,
        val didDoc: DidDoc? = null
    ) : AtProtocolModel
}