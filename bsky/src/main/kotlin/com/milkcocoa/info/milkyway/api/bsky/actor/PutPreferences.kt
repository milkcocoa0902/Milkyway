package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolUnit
import com.milkcocoa.info.milkyway.models.bsky.actor.defs.ActorPreferenceDef
import kotlinx.serialization.Serializable

class PutPreferences(val domain: Domain) :
    AtProtocolPost<PutPreferences.PutPreferencesRequest, AtProtocolUnit>(
        action = BskyActions.PutPreferences,
        domain = domain,
        PutPreferencesRequest::class,
        AtProtocolUnit::class
    ) {
    @Serializable
    data class PutPreferencesRequest(
        override val accessJwt: String,
        val preferences: List<ActorPreferenceDef>
    ) : AtProtocolRequestWithSession
}