package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable

class GetFollowers(val domain: Domain):
    AtProtocolGet<GetFollowers.GetFollowersRequest, GetFollowers.GetFollowersResponse>(
    action = BskyActions.GetFollowers,
    domain = domain,
    GetFollowersRequest::class,
    GetFollowersResponse::class
) {
    @Serializable
    data class GetFollowersRequest(
        override val accessJwt: String,
        val actor: String,
        val limit: Int = 50,
        val cursor: String? = null,
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetFollowersResponse(
        val subject: ProfileView,
        val cursor: String = "",
        val followers: List<ProfileView>
    ): AtProtocolModel
}