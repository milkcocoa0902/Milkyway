package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.domain.Domain

class Repo(private val domain: Domain) {
    suspend fun createRecord(request: CreateRecord.CreateRecordRequest) = CreateRecord(domain).execute(request)
}