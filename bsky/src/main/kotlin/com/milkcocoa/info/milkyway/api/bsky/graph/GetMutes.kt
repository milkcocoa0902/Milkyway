package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable

/**
 * Enumerates accounts that the requesting account (actor) currently has muted. Requires auth.
 */
class GetMutes(val domain: Domain) :
    AtProtocolGet<GetMutes.GetMutesRequest, GetMutes.GetMutesResponse>(
        action = BskyActions.GetMutes,
        domain = domain,
        GetMutesRequest::class,
        GetMutesResponse::class
    ) {
    @Serializable
    data class GetMutesRequest(
        override val accessJwt: String,
        val limit: Int = 50,
        val cursor: String? = null
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetMutesResponse(
        val cursor: String = "",
        val mutes: List<ProfileView>
    ) : AtProtocolModel
}