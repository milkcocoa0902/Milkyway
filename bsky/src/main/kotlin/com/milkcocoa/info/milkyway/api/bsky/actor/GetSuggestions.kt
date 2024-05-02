package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetSuggestions(val domain: Domain) :
    AtProtocolGet<GetSuggestions.GetSuggestionsRequest, GetSuggestions.GetSuggestionsResponse>(
        action = BskyActions.GetSuggestions,
        domain = domain,
        GetSuggestionsRequest::class,
        GetSuggestionsResponse::class
    ) {
    @Serializable
    data class GetSuggestionsRequest(
        @Transient
        override val accessJwt: String = "",
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetSuggestionsResponse(
        val cursor: String = "",
        val actors: List<ProfileView> = emptyList()
    ) : AtProtocolModel
}