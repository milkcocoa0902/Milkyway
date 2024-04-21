package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable

class GetSuggestions(val domain: Domain) :
    AtProtocolGet<GetSuggestions.GetSuggestionsRequest, GetSuggestions.GetSuggestionsResponse>(
        action = BskyActions.GetSuggestions,
        domain = domain,
        GetSuggestionsRequest::class,
        GetSuggestionsResponse::class
    ) {
    @Serializable
    data class GetSuggestionsRequest(
        override val accessJwt: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetSuggestionsResponse(
        val cursor: String = "",
        val actors: List<ProfileView> = emptyList()
    ) : AtProtocolModel
}