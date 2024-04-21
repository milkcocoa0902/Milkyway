package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import kotlinx.serialization.Serializable

/**
 * Initiate a user account password reset via email.
 */
class RequestPasswordReset(val domain: Domain) :
    AtProtocolUnitPost<RequestPasswordReset.RequestPasswordResetRequest>(
        AtProtoActions.RequestPasswordReset,
        domain,
        RequestPasswordResetRequest::class
    ) {
    @Serializable
    data class RequestPasswordResetRequest(
        val email: String
    ) : AtProtocolRequest
}