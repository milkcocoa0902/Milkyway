package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

/**
 * Initiate a user account deletion via email.
 */
class RequestAccountDelete(val domain: Domain) :
    AtProtocolUnitPost<RequestAccountDelete.RequestAccountDeleteRequest>(
        AtProtoActions.RequestAccountDelete,
        domain,
        RequestAccountDeleteRequest::class
    ) {
    @Serializable
    data class RequestAccountDeleteRequest(
        override val accessJwt: String
    ) : AtProtocolRequestWithSession
}