package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.domain.Domain

class Feed(
    private val domain: Domain
) {
    suspend fun getTimeline(request: GetTimeLine.GetTimelineRequest) = GetTimeLine(domain).execute(request)
}