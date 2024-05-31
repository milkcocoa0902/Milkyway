package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import com.milkcocoa.info.milkyway.models.aturi.Handle
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Resolves a handle (domain name) to a DID.
 */
class ResolveHandle(val domain: Domain) :
    AtProtocolGet<ResolveHandle.ResolveHandleRequest, ResolveHandle.ResolveHandleResponse>(
        AtProtoActions.ResolveHandle,
        domain,
        ResolveHandleRequest::class,
        ResolveHandleResponse::class
    ) {
    @Serializable
    data class ResolveHandleRequest(
        @Transient
        override val accessJwt: String = "",
        /**
         * The handle to resolve.
         */
        val handle: Handle
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class ResolveHandleResponse(
        val did: Did
    ) : AtProtocolModel
}