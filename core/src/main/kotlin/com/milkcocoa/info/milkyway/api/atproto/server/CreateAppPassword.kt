package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * Create an App Password.
 */
class CreateAppPassword(val domain: Domain) :
    AtProtocolPost<CreateAppPassword.CreateAppPasswordRequest, CreateAppPassword.CreateAppPasswordResponse>(
        AtProtoActions.CreateAppPassword,
        domain,
        CreateAppPasswordRequest::class,
        CreateAppPasswordResponse::class
    ) {
    @Serializable
    data class CreateAppPasswordRequest(
        override val accessJwt: String,
        /**
         * A short name for the App Password, to help distinguish them.
         */
        val name: String
    ) : AtProtocolRequestWithSession

    @Serializable
    data class CreateAppPasswordResponse(
        val name: String,
        val password: String,
        @Serializable(with = DateTimeSerializer::class)
        val createdAt: LocalDateTime
    ) : AtProtocolModel
}