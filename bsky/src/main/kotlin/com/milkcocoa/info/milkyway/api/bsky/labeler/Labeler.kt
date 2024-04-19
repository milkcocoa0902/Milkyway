package com.milkcocoa.info.milkyway.api.bsky.labeler

import com.milkcocoa.info.milkyway.domain.Domain

class Labeler(val domain: Domain) {
    suspend fun getServices(request: GetServices.GetServicesRequest) = GetServices(domain).execute(request)
}