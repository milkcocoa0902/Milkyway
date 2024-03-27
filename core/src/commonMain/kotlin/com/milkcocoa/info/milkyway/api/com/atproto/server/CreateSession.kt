package com.milkcocoa.info.milkyway.api.com.atproto.server

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.atproto.models.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkyway.atproto.models.com.atproto.server.CreateSessionResponse
import com.milkcocoa.info.milkyway.domain.Domain

class CreateSession(val domain: Domain): AtProtocolPost<CreateSessionRequest, CreateSessionResponse>(
    AtProtoActions.ATPROTO_CREATE_SESSION,
    domain,
    CreateSessionRequest::class,
    CreateSessionResponse::class
)

