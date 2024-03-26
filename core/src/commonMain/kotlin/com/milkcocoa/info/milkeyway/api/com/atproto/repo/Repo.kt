package com.milkcocoa.info.milkeyway.api.com.atproto.repo

import com.milkcocoa.info.milkeyway.api.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkeyway.domain.Domain

class Repo(private val domain: Domain) {
    suspend fun createRecord(request: CreateRecordRequest, accessJwt: String) = CreateRecord(domain).execute(request, accessJwt)
}