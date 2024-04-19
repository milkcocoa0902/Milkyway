package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

/**
 * Unmutes the specified account. Requires auth.
 */
class UnMuteActor(val domain: Domain) :
    AtProtocolUnitPost<UnMuteActor.UnMuteActorRequest>(
        action = BskyActions.UnMuteActor,
        domain = domain,
        UnMuteActorRequest::class
    ) {
    @Serializable
    data class UnMuteActorRequest(
        override val accessJwt: String,
        val actor: String
    ) : AtProtocolRequestWithSession
}