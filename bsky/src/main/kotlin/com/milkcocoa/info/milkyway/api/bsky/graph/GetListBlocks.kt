package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListView
import kotlinx.serialization.Serializable

class GetListBlocks(val domain: Domain) :
    AtProtocolGet<GetListBlocks.GetListBlocksRequest, GetListBlocks.GetListBlocksResponse>(
        action = BskyActions.GetListBlocks,
        domain = domain,
        GetListBlocksRequest::class,
        GetListBlocksResponse::class
    ) {
    @Serializable
    data class GetListBlocksRequest(
        override val accessJwt: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetListBlocksResponse(
        val cursor: String = "",
        val lists: List<ListView>
    ) : AtProtocolModel
}