package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.FeedViewPost
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetTimeLine(val domain: Domain) : AtProtocolGet<GetTimeLine.GetTimelineRequest, GetTimeLine.GetTimelineResponse>(
    action = BskyActions.GetTimeLine,
    domain = domain,
    GetTimelineRequest::class,
    GetTimelineResponse::class
) {
    @Serializable
    data class GetTimelineRequest(
        @Transient
        override val accessJwt: String = "",
        val cursor: String = "",
        val limit: Int = 50
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetTimelineResponse(
        val cursor: String = "",
        val feed: List<FeedViewPost>
    ) : AtProtocolModel
}