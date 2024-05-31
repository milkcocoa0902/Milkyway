package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolPost
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
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
        @Transient
        override val accessJwt: String = "",
        /**
         * A short name for the App Password, to help distinguish them.
         */
        val name: String
    ) : RequireUserSession, AtProtocolPostRequestModel

    @Serializable
    data class CreateAppPasswordResponse(
        val name: String,
        val password: String,
        @Serializable(with = DateTimeSerializer::class)
        val createdAt: LocalDateTime
    ) : AtProtocolModel
}