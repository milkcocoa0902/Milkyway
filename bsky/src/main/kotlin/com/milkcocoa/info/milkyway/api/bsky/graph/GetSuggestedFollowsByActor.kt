package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable

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
        override val accessJwt: String,
        val actor: String
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetSuggestedFoloowsByActorResponse(
        val cursor: String = "",
        val items: List<ProfileView>
    ) : AtProtocolModel
}