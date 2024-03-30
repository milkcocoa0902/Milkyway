package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.feed.FeedEntity
import kotlinx.serialization.Serializable

class GetTimeLine(val domain: Domain): AtProtocolGet<GetTimeLine.GetTimelineRequest, GetTimeLine.GetTimelineResponse>(
    action = BskyActions.GetTimeLine,
    domain = domain,
    GetTimelineRequest::class,
    GetTimelineResponse::class
){

    @Serializable
    data class GetTimelineRequest(
        override val accessJwt: String,
        val cursor: String,
        val limit: Int
    ): AtProtocolRequestWithSession


    @Serializable
    data class GetTimelineResponse(
        val feed: List<FeedEntity>
    ): AtProtocolModel

}