package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.did.DidDoc
import kotlinx.serialization.Serializable

class CreateSession(val domain: Domain) :
    AtProtocolPost<CreateSession.CreateSessionRequest, CreateSession.CreateSessionResponse>(
        AtProtoActions.ATPROTO_CREATE_SESSION,
        domain,
        CreateSessionRequest::class,
        CreateSessionResponse::class
    ) {
    @Serializable
    data class CreateSessionRequest(
        val identifier: String,
        val password: String
    ) : AtProtocolRequest

    @Serializable
    data class CreateSessionResponse(
        val did: String,
        val didDoc: DidDoc,
        val handle: String,
        val email: String,
        val emailConfirmed: Boolean,
        val accessJwt: String,
        val refreshJwt: String
    ) : AtProtocolModel
}