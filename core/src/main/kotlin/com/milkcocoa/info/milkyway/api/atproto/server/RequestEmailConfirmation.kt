package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Request an email with a code to confirm ownership of email.
 */
class RequestEmailConfirmation(val domain: Domain) :
    AtProtocolUnitPost<RequestEmailConfirmation.RequestEmailConfirmationRequest>(
        AtProtoActions.RequestEmailConfirmation,
        domain,
        RequestEmailConfirmationRequest::class
    ) {
    @Serializable
    data class RequestEmailConfirmationRequest(
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolPostRequestModel
}