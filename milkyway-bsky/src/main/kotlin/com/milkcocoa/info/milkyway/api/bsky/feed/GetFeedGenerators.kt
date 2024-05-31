package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetFeedGenerators(val domain: Domain) :
    AtProtocolGet<GetFeedGenerators.GetFeedGeneratorsRequest, GetFeedGenerators.GetFeedGeneratorsResponse>(
        action = BskyActions.GetFeedGenerators,
        domain = domain,
        GetFeedGeneratorsRequest::class,
        GetFeedGeneratorsResponse::class
    ) {
    @Serializable
    data class GetFeedGeneratorsRequest(
        @Transient
        override val accessJwt: String = "",
        val feeds: List<AtUri>
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetFeedGeneratorsResponse(
        val feeds: List<GeneratorView>
    ) : AtProtocolModel
}