package com.milkcocoa.info.milkyway.api.com.atproto.server

import com.milkcocoa.info.milkyway.atproto.models.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkyway.domain.Domain

class Server(private val domain: Domain) {
    suspend fun createSession(request: CreateSessionRequest) = CreateSession(domain).execute(request)
}