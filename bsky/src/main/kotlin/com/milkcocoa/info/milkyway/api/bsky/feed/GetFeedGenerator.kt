package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import kotlinx.serialization.Serializable

class GetFeedGenerator(val domain: Domain) :
    AtProtocolGet<GetFeedGenerator.GetFeedGeneratorRequest, GetFeedGenerator.GetFeedGeneratorResponse>(
        action = BskyActions.GetFeedGenerator,
        domain = domain,
        GetFeedGeneratorRequest::class,
        GetFeedGeneratorResponse::class
    ) {
    @Serializable
    data class GetFeedGeneratorRequest(
        override val accessJwt: String,
        val feed: AtUri
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetFeedGeneratorResponse(
        val view: GeneratorView,
        val isOnline: Boolean,
        val isValid: Boolean
    ) : AtProtocolModel
}