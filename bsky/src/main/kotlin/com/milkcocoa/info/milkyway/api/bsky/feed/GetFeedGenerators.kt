package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import kotlinx.serialization.Serializable

class GetFeedGenerators(val domain: Domain) :
    AtProtocolGet<GetFeedGenerators.GetFeedGeneratorsRequest, GetFeedGenerators.GetFeedGeneratorsResponse>(
        action = BskyActions.GetFeedGenerators,
        domain = domain,
        GetFeedGeneratorsRequest::class,
        GetFeedGeneratorsResponse::class
) {
    @Serializable
    data class GetFeedGeneratorsRequest(
        override val accessJwt: String,
        val feeds: List<String>
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetFeedGeneratorsResponse(
        val feeds: List<GeneratorView>
    ): AtProtocolModel
}