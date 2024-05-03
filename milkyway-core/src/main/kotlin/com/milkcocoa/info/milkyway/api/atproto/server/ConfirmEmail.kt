package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Confirm an email using a token from com.atproto.server.requestEmailConfirmation.
 */
class ConfirmEmail(val domain: Domain) :
    AtProtocolUnitPost<ConfirmEmail.ConfirmEmailRequest>(
        AtProtoActions.ConfirmEmail,
        domain,
        ConfirmEmailRequest::class
    ) {
    @Serializable
    data class ConfirmEmailRequest(
        @Transient
        override val accessJwt: String = "",
        val email: String,
        val token: String
    ) : RequireUserSession, AtProtocolPostRequestModel
}