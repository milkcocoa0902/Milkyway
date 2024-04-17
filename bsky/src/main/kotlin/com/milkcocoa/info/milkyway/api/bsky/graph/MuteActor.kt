package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolUnit
import kotlinx.serialization.Serializable

/**
 * Creates a mute relationship for the specified account. Mutes are private in Bluesky. Requires auth.
 */
class MuteActor(val domain: Domain):
    AtProtocolPost<MuteActor.MuteActorRequest, AtProtocolUnit>(
    action = BskyActions.MuteActor,
    domain = domain,
    MuteActorRequest::class,
    AtProtocolUnit::class
) {
    @Serializable
    data class MuteActorRequest(
        override val accessJwt: String,
        val actor: String
    ) : AtProtocolRequestWithSession
}