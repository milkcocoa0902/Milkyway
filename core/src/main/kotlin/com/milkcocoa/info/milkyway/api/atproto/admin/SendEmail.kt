package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithAdmin
import kotlinx.serialization.Serializable

/**
 * Send email to a user's account email address.
 */
class SendEmail(val domain: Domain) :
    AtProtocolPost<SendEmail.SendEmailRequest, SendEmail.SendEmailResponse>(
        AtProtoActions.AdminSendEmail,
        domain,
        SendEmailRequest::class,
        SendEmailResponse::class
    ) {
    @Serializable
    data class SendEmailRequest(
        override val adminPassword: String,
        val recipientDid: String,
        val content: String,
        val subject: String? = null,
        val senderDid: String,
        /**
         * Additional comment by the sender that won't be used in the email itself but helpful to provide more context for moderators/reviewers
         */
        val comment: String? = null
    ) : AtProtocolRequestWithAdmin

    @Serializable
    data class SendEmailResponse(
        val sent: Boolean
    ) : AtProtocolModel
}