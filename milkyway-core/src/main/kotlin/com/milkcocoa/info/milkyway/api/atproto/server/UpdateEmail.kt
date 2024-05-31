package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Update an account's email.
 */
class UpdateEmail(val domain: Domain) :
    AtProtocolUnitPost<UpdateEmail.UpdateEmailRequest>(
        AtProtoActions.UpdateEmail,
        domain,
        UpdateEmailRequest::class
    ) {
    @Serializable
    data class UpdateEmailRequest(
        @Transient
        override val accessJwt: String = "",
        val email: String,
        /**
         * Requires a token from com.atproto.sever.requestEmailUpdate if the account's email has been confirmed.
         */
        val token: String? = null
    ) : RequireUserSession, AtProtocolPostRequestModel
}