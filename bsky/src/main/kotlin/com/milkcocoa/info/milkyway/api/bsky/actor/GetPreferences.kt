package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.actor.defs.ActorPreferenceDef
import kotlinx.serialization.Serializable

class GetPreferences(val domain: Domain) :
    AtProtocolGet<GetPreferences.GetPreferencesRequest, GetPreferences.GetPreferencesResponse>(
        action = BskyActions.GetPreferences,
        domain = domain,
        GetPreferencesRequest::class,
        GetPreferencesResponse::class
    ) {
    @Serializable
    data class GetPreferencesRequest(
        override val accessJwt: String
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetPreferencesResponse(
        val preferences: List<ActorPreferenceDef>
    ) : AtProtocolModel
}