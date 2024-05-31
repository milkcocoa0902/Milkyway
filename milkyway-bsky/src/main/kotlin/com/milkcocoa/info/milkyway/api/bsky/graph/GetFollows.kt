package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetFollows(val domain: Domain) :
    AtProtocolGet<GetFollows.GetFollowsRequest, GetFollows.GetFollowsResponse>(
        action = BskyActions.GetFollows,
        domain = domain,
        GetFollowsRequest::class,
        GetFollowsResponse::class
    ) {
    @Serializable
    data class GetFollowsRequest(
        @Transient
        override val accessJwt: String = "",
        val actor: ATIdentifier,
        val limit: Int = 50,
        val cursor: String? = null
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetFollowsResponse(
        val subject: ProfileView,
        val cursor: String = "",
        val follows: List<ProfileView>
    ) : AtProtocolModel
}