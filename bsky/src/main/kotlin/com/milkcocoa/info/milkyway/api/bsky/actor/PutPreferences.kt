package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.actor.defs.ActorPreferenceDef
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class PutPreferences(val domain: Domain) :
    AtProtocolUnitPost<PutPreferences.PutPreferencesRequest>(
        action = BskyActions.PutPreferences,
        domain = domain,
        PutPreferencesRequest::class
    ) {
    @Serializable
    data class PutPreferencesRequest(
        @Transient
        override val accessJwt: String = "",
        val preferences: List<ActorPreferenceDef>
    ) : RequireUserSession, AtProtocolPostRequestModel
}