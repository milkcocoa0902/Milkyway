package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import kotlinx.serialization.Serializable

/**
 * Reset a user account password using a token.
 */
class ResetPassword(val domain: Domain) :
    AtProtocolUnitPost<ResetPassword.ResetPasswordRequest>(
        AtProtoActions.ResetPassword,
        domain,
        ResetPasswordRequest::class
    ) {
    @Serializable
    data class ResetPasswordRequest(
        val token: String,
        val password: String
    ) : AtProtocolRequest, AtProtocolPostRequestModel
}