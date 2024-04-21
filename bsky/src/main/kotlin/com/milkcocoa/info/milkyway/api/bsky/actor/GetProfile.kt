package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ActorProfileViewDetails
import kotlinx.serialization.Serializable

class GetProfile(val domain: Domain) : AtProtocolGet<GetProfile.GetProfileRequest, ActorProfileViewDetails>(
    action = BskyActions.GetProfile,
    domain = domain,
    GetProfileRequest::class,
    ActorProfileViewDetails::class
) {
    @Serializable
    data class GetProfileRequest(
        override val accessJwt: String,
        val actor: String
    ) : AtProtocolRequestWithSession
}