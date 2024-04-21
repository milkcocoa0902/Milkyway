package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.FeedViewPost
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetFeed(val domain: Domain) :
    AtProtocolGet<GetFeed.GetFeedRequest, GetFeed.GetFeedResponse>(
        action = BskyActions.GetFeed,
        domain = domain,
        GetFeedRequest::class,
        GetFeedResponse::class
    ) {
    @Serializable
    data class GetFeedRequest(
        override val accessJwt: String,
        val feed: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetFeedResponse(
        val cursor: String = "",
        val feeds: List<FeedViewPost>
    ) : AtProtocolModel
}