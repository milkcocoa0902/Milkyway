package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ActorProfileViewDetails
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetProfiles(val domain: Domain) : AtProtocolGet<GetProfiles.GetProfilesRequest, GetProfiles.GetProfilesResponse>(
    action = BskyActions.GetProfiles,
    domain = domain,
    GetProfilesRequest::class,
    GetProfilesResponse::class
) {
    @Serializable
    data class GetProfilesRequest(
        override val accessJwt: String,
        val actors: List<String>
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetProfilesResponse(
        val profiles: List<ActorProfileViewDetails>
    ) : AtProtocolModel
}