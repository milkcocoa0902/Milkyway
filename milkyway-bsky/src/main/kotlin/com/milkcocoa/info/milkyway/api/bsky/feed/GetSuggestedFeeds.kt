package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetSuggestedFeeds(val domain: Domain) :
    AtProtocolGet<GetSuggestedFeeds.GetSuggestedFeedsRequest, GetSuggestedFeeds.GetSuggestedFeedsResponse>(
        action = BskyActions.GetSuggestedFeeds,
        domain = domain,
        GetSuggestedFeedsRequest::class,
        GetSuggestedFeedsResponse::class
    ) {
    @Serializable
    data class GetSuggestedFeedsRequest(
        @Transient
        override val accessJwt: String = "",
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetSuggestedFeedsResponse(
        val cursor: String = "",
        val repostedBy: List<GeneratorView>
    ) : AtProtocolModel
}