package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable

class GetFollows(val domain: Domain) :
    AtProtocolGet<GetFollows.GetFollowsRequest, GetFollows.GetFollowsResponse>(
        action = BskyActions.GetFollows,
        domain = domain,
        GetFollowsRequest::class,
        GetFollowsResponse::class
    ) {
    @Serializable
    data class GetFollowsRequest(
        override val accessJwt: String,
        val actor: String,
        val limit: Int = 50,
        val cursor: String? = null
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetFollowsResponse(
        val subject: ProfileView,
        val cursor: String = "",
        val follows: List<ProfileView>
    ) : AtProtocolModel
}