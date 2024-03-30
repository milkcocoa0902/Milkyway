package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.domain.Domain

class Server(private val domain: Domain) {
    suspend fun createSession(request: CreateSession.CreateSessionRequest) = CreateSession(domain).execute(request)
}