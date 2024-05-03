package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Enumerates the lists created by a specified account (actor).
 */
class GetLists(val domain: Domain) :
    AtProtocolGet<GetLists.GetListsRequest, GetLists.GetListsResponse>(
        action = BskyActions.GetLists,
        domain = domain,
        GetListsRequest::class,
        GetListsResponse::class
    ) {
    @Serializable
    data class GetListsRequest(
        @Transient
        override val accessJwt: String = "",
        /**
         * The account (actor) to enumerate lists from.
         */
        val actor: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetListsResponse(
        val cursor: String = "",
        val lists: List<ListView>
    ) : AtProtocolModel
}