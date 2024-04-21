package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import kotlinx.serialization.Serializable

class GetSuggestedFeeds(val domain: Domain) :
    AtProtocolGet<GetSuggestedFeeds.GetSuggestedFeedsRequest, GetSuggestedFeeds.GetSuggestedFeedsResponse>(
        action = BskyActions.GetSuggestedFeeds,
        domain = domain,
        GetSuggestedFeedsRequest::class,
        GetSuggestedFeedsResponse::class
    ) {
    @Serializable
    data class GetSuggestedFeedsRequest(
        override val accessJwt: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetSuggestedFeedsResponse(
        val cursor: String = "",
        val repostedBy: List<GeneratorView>
    ) : AtProtocolModel
}