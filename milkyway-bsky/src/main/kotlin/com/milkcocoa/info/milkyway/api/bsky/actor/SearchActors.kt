package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileViewBasic
import kotlinx.serialization.Serializable

class SearchActors(val domain: Domain) :
    AtProtocolGet<SearchActors.SearchActorsRequest, SearchActors.SearchActorsResponse>(
        action = BskyActions.SearchActors,
        domain = domain,
        SearchActorsRequest::class,
        SearchActorsResponse::class
    ) {
    @Serializable
    data class SearchActorsRequest(
        val q: String,
        val limit: Int = 10
    ) : AtProtocolRequest, AtProtocolGetRequestModel

    @Serializable
    data class SearchActorsResponse(
        val actors: List<ProfileViewBasic> = emptyList()
    ) : AtProtocolModel
}