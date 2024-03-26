package com.milkcocoa.info.milkeyway.api.com.atproto.server

import com.milkcocoa.info.milkeyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkeyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkeyway.atproto.models.AtProtocolRequest
import com.milkcocoa.info.milkeyway.atproto.models.Session
import com.milkcocoa.info.milkeyway.domain.Domain
import kotlinx.serialization.Serializable

class CreateSession(val domain: Domain): AtProtocolPost<CreateSessionRequest, Session>(
    AtProtoActions.ATPROTO_CREATE_SESSION,
    domain,
    CreateSessionRequest::class,
    Session::class
)

@Serializable
data class CreateSessionRequest(
    val identifier: String,
    val password: String
): AtProtocolRequest