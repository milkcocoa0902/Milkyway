package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.api.bsky.feed.GetTimeLine
import com.milkcocoa.info.milkyway.domain.Domain

class Actor(
    private val domain: Domain
) {
    suspend fun getProfile(request: GetProfile.GetProfileRequest) = GetProfile(domain).execute(request)
}