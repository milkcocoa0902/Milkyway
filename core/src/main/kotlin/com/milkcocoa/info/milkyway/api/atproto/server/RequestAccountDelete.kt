package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolPostRequestModel
}