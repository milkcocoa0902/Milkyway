package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Get a signed token on behalf of the requesting DID for the requested service.
 */
class GetServiceAuth(val domain: Domain) :
    AtProtocolGet<GetServiceAuth.GetServiceAuthRequest, GetServiceAuth.GetServiceAuthResponse>(
        AtProtoActions.GetServiceAuth,
        domain,
        GetServiceAuthRequest::class,
        GetServiceAuthResponse::class
    ) {
    @Serializable
    data class GetServiceAuthRequest(
        @Transient
        override val accessJwt: String = "",
        /**
         * The DID of the service that the token will be used to authenticate with
         */
        val aud: String
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetServiceAuthResponse(
        val token: String
    ) : AtProtocolModel
}