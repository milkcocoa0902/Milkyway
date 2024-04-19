package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.FeedViewPost
import kotlinx.serialization.Serializable

class GetListFeed(val domain: Domain) :
    AtProtocolGet<GetListFeed.GetListFeedRequest, GetListFeed.GetListFeedResponse>(
        action = BskyActions.GetListFeed,
        domain = domain,
        GetListFeedRequest::class,
        GetListFeedResponse::class
    ) {
    @Serializable
    data class GetListFeedRequest(
        val list: List<String>,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequest

    @Serializable
    data class GetListFeedResponse(
        val cursor: String = "",
        val feeds: List<FeedViewPost>
    ) : AtProtocolModel
}