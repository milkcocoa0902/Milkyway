package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.atproto.server.defs.AppPassword
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * List all App Passwords.
 */
class ListAppPasswords(val domain: Domain) :
    AtProtocolGet<ListAppPasswords.ListAppPasswordsRequest, ListAppPasswords.ListAppPasswordsResponse>(
        AtProtoActions.ListAppPasswords,
        domain,
        ListAppPasswordsRequest::class,
        ListAppPasswordsResponse::class
    ) {
    @Serializable
    data class ListAppPasswordsRequest(
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class ListAppPasswordsResponse(
        val passwords: List<AppPassword>
    ) : AtProtocolModel
}