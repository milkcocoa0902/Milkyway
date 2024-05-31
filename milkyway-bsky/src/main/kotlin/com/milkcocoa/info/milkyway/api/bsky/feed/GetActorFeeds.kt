package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetActorFeeds(val domain: Domain) :
    AtProtocolGet<GetActorFeeds.GetActorFeedsRequest, GetActorFeeds.GetActorFeedsResponse>(
        action = BskyActions.GetActorFeeds,
        domain = domain,
        GetActorFeedsRequest::class,
        GetActorFeedsResponse::class
    ) {
    @Serializable
    data class GetActorFeedsRequest(
        @Transient
        override val accessJwt: String = "",
        val actor: ATIdentifier,
        val limit: Int = 50,
        val cursor: String
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetActorFeedsResponse(
        val cursor: String = "",
        val feeds: List<GeneratorView>
    ) : AtProtocolModel
}