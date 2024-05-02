package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.aturi.AtIdentifierListSerializer
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
        @Transient
        override val accessJwt: String = "",
        @Serializable(with = AtIdentifierListSerializer::class)
        val actors: List<ATIdentifier>
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetProfilesResponse(
        val profiles: List<ActorProfileViewDetails>
    ) : AtProtocolModel
}