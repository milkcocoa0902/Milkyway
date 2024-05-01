package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import kotlinx.serialization.Serializable

/**
 * Creates a mute relationship for the specified account. Mutes are private in Bluesky. Requires auth.
 */
class MuteActor(val domain: Domain) :
    AtProtocolUnitPost<MuteActor.MuteActorRequest>(
        action = BskyActions.MuteActor,
        domain = domain,
        MuteActorRequest::class
    ) {
    @Serializable
    data class MuteActorRequest(
        override val accessJwt: String,
        val actor: ATIdentifier
    ) : RequireUserSession, AtProtocolPostRequestModel
}