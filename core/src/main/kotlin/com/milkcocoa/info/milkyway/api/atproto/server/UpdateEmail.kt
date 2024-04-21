package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

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
        override val accessJwt: String,
        val email: String,
        /**
         * Requires a token from com.atproto.sever.requestEmailUpdate if the account's email has been confirmed.
         */
        val token: String? = null
    ) : AtProtocolRequestWithSession
}