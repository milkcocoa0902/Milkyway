package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireAdminSession
import com.milkcocoa.info.milkyway.models.aturi.Did
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val adminPassword: String = "",
        val recipientDid: Did,
        val content: String,
        val subject: String? = null,
        val senderDid: Did,
        /**
         * Additional comment by the sender that won't be used in the email itself but helpful to provide more context for moderators/reviewers
         */
        val comment: String? = null
    ) : RequireAdminSession, AtProtocolPostRequestModel

    @Serializable
    data class SendEmailResponse(
        val sent: Boolean
    ) : AtProtocolModel
}