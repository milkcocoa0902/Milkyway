package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.FeedViewPost
import kotlinx.serialization.Serializable

class GetActorLikes(val domain: Domain) :
    AtProtocolGet<GetActorLikes.GetActorLikesRequest, GetActorLikes.GetActorLikesResponse>(
    action = BskyActions.GetActorLikes,
    domain = domain,
    GetActorLikesRequest::class,
    GetActorLikesResponse::class
) {
    @Serializable
    data class GetActorLikesRequest(
        val actor: String,
        val limit: Int = 50,
        val cursor: String
    ) : AtProtocolRequest

    @Serializable
    data class GetActorLikesResponse(
        val cursor: String = "",
        val feeds: List<FeedViewPost>
    ): AtProtocolModel
}