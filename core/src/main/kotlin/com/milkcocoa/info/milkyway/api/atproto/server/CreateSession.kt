package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
import com.milkcocoa.info.milkyway.models.did.DidDoc
import kotlinx.serialization.Serializable

class CreateSession(val domain: Domain) :
    AtProtocolPost<CreateSession.CreateSessionRequest, CreateSession.CreateSessionResponse>(
        AtProtoActions.CreateSession,
        domain,
        CreateSessionRequest::class,
        CreateSessionResponse::class
    ) {
    @Serializable
    data class CreateSessionRequest(
        val identifier: String,
        val password: String
    ) : AtProtocolRequest, AtProtocolPostRequestModel

    @Serializable
    data class CreateSessionResponse(
        val did: Did,
        val didDoc: DidDoc? = null,
        val handle: Handle,
        val email: String? = null,
        val emailConfirmed: Boolean? = null,
        val emailAuthFactor: Boolean? = null,
        val accessJwt: String,
        val refreshJwt: String
    ) : AtProtocolModel
}