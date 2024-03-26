package com.milkcocoa.info.milkeyway.api.com.atproto.server

import com.milkcocoa.info.milkeyway.domain.Domain

class Server(private val domain: Domain) {
    suspend fun createSession(request: CreateSessionRequest) = CreateSession(domain).execute(request)
}