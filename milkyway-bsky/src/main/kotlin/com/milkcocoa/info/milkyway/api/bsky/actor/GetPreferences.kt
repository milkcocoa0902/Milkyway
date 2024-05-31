package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.actor.defs.ActorPreferenceDef
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetPreferences(val domain: Domain) :
    AtProtocolGet<GetPreferences.GetPreferencesRequest, GetPreferences.GetPreferencesResponse>(
        action = BskyActions.GetPreferences,
        domain = domain,
        GetPreferencesRequest::class,
        GetPreferencesResponse::class
    ) {
    @Serializable
    data class GetPreferencesRequest(
        @Transient
        override val accessJwt: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetPreferencesResponse(
        val preferences: List<ActorPreferenceDef>
    ) : AtProtocolModel
}