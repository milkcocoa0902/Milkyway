package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.AtProtocolUnit
import kotlinx.serialization.Serializable

/**
 * Unmutes the specified account. Requires auth.
 */
class UnMuteActor(val domain: Domain):
    AtProtocolPost<UnMuteActor.UnMuteActorRequest, AtProtocolUnit>(
    action = BskyActions.UnMuteActor,
    domain = domain,
    UnMuteActorRequest::class,
    AtProtocolUnit::class
) {
    @Serializable
    data class UnMuteActorRequest(
        override val accessJwt: String,
        val actor: String
    ) : AtProtocolRequestWithSession
}