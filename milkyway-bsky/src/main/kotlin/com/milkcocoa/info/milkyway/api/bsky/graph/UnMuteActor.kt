package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val accessJwt: String = "",
        val actor: ATIdentifier
    ) : RequireUserSession, AtProtocolPostRequestModel
}