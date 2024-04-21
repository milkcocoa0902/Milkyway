package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import kotlinx.serialization.Serializable

class GetActorFeeds(val domain: Domain) :
    AtProtocolGet<GetActorFeeds.GetActorFeedsRequest, GetActorFeeds.GetActorFeedsResponse>(
        action = BskyActions.GetActorFeeds,
        domain = domain,
        GetActorFeedsRequest::class,
        GetActorFeedsResponse::class
    ) {
    @Serializable
    data class GetActorFeedsRequest(
        override val accessJwt: String,
        val actor: String,
        val limit: Int = 50,
        val cursor: String
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetActorFeedsResponse(
        val cursor: String = "",
        val feeds: List<GeneratorView>
    ) : AtProtocolModel
}