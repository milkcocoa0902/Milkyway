package com.milkcocoa.info.milkyway.api.com.atproto.repo

import com.milkcocoa.info.milkyway.atproto.models.com.atproto.repo.CreateRecordRequest
import com.milkcocoa.info.milkyway.domain.Domain

class Repo(private val domain: Domain) {
    suspend fun createRecord(request: CreateRecordRequest, accessJwt: String) = CreateRecord(domain).execute(request, accessJwt)
}