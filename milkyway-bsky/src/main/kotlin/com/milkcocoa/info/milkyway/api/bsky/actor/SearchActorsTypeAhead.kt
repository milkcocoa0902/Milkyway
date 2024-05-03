package com.milkcocoa.info.milkyway.api.bsky.actor

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileViewBasic
import kotlinx.serialization.Serializable

class SearchActorsTypeAhead(val domain: Domain) :
    AtProtocolGet<
        SearchActorsTypeAhead.SearchActorsTypeAheadRequest,
        SearchActorsTypeAhead.SearchActorsTypeAheadResponse
    >(
        action = BskyActions.SearchActorsTypeAhead,
        domain = domain,
        SearchActorsTypeAheadRequest::class,
        SearchActorsTypeAheadResponse::class
    ) {
    @Serializable
    data class SearchActorsTypeAheadRequest(
        val q: String,
        val limit: Int = 10
    ) : AtProtocolRequest, AtProtocolGetRequestModel

    @Serializable
    data class SearchActorsTypeAheadResponse(
        val actors: List<ProfileViewBasic> = emptyList()
    ) : AtProtocolModel
}