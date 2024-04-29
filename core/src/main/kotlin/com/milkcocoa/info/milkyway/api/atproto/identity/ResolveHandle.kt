package com.milkcocoa.info.milkyway.api.atproto.identity

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

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
        override val accessJwt: String,
        /**
         * The handle to resolve.
         */
        val handle: String
    ) : AtProtocolRequestWithSession

    @Serializable
    data class ResolveHandleResponse(
        val did: String
    ) : AtProtocolModel
}