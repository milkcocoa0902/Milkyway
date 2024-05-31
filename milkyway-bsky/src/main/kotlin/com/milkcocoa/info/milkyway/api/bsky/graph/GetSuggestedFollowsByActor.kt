package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Enumerates follows similar to a given account (actor). Expected use is to recommend additional accounts immediately after following one account.
 */
class GetSuggestedFollowsByActor(val domain: Domain) :
    AtProtocolGet<
        GetSuggestedFollowsByActor.GetSuggestedFollowsByActorRequest,
        GetSuggestedFollowsByActor.GetSuggestedFoloowsByActorResponse
    >(
        action = BskyActions.GetSuggestedFollowsByActor,
        domain = domain,
        GetSuggestedFollowsByActorRequest::class,
        GetSuggestedFoloowsByActorResponse::class
    ) {
    @Serializable
    data class GetSuggestedFollowsByActorRequest(
        @Transient
        override val accessJwt: String = "",
        val actor: ATIdentifier
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetSuggestedFoloowsByActorResponse(
        val cursor: String = "",
        val items: List<ProfileView>
    ) : AtProtocolModel
}