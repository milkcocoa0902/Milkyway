package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ActorProfileViewDetails
import com.milkcocoa.info.milkyway.models.bsky.actor.defs.ActorPreferenceDef
import kotlinx.serialization.Serializable

class GetPreferences(val domain: Domain): AtProtocolGet<GetPreferences.GetPreferencesRequest, GetPreferences.GetPreferencesResponse>(
    action = BskyActions.GetPreferences,
    domain = domain,
    GetPreferencesRequest::class,
    GetPreferencesResponse::class
){

    @Serializable
    data class GetPreferencesRequest(
        override val accessJwt: String,
    ): AtProtocolRequestWithSession

    @Serializable
    data class GetPreferencesResponse(
        val preferences: List<ActorPreferenceDef>
    ): AtProtocolModel
}