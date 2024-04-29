package com.milkcocoa.info.milkyway.api.atproto.moderation

import com.milkcocoa.info.milkyway.domain.Domain

class Moderation(val domain: Domain) {
    suspend fun createReport(request: CreateReport.CreateReportRequest) = CreateReport(domain = domain).execute(request)
}