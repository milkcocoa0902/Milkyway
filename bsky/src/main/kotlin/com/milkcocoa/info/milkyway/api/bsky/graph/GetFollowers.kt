package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable

class GetFollowers(val domain: Domain) :
    AtProtocolGet<GetFollowers.GetFollowersRequest, GetFollowers.GetFollowersResponse>(
        action = BskyActions.GetFollowers,
        domain = domain,
        GetFollowersRequest::class,
        GetFollowersResponse::class
    ) {
    @Serializable
    data class GetFollowersRequest(
        override val accessJwt: String,
        val actor: ATIdentifier,
        val limit: Int = 50,
        val cursor: String? = null
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetFollowersResponse(
        val subject: ProfileView,
        val cursor: String = "",
        val followers: List<ProfileView>
    ) : AtProtocolModel
}