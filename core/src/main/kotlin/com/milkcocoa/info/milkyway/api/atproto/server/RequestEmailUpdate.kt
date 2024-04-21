package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

/**
 * Request a token in order to update email.
 */
class RequestEmailUpdate(val domain: Domain) :
    AtProtocolPost<RequestEmailUpdate.RequestEmailUpdateRequest, RequestEmailUpdate.RequestEmailUpdateResponse>(
        AtProtoActions.RequestEmailUpdate,
        domain,
        RequestEmailUpdateRequest::class,
        RequestEmailUpdateResponse::class
    ) {
    @Serializable
    data class RequestEmailUpdateRequest(
        override val accessJwt: String
    ) : AtProtocolRequestWithSession

    @Serializable
    data class RequestEmailUpdateResponse(
        val tokenRequired: Boolean
    ) : AtProtocolModel
}