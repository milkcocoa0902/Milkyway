package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListView
import kotlinx.serialization.Serializable

class GetListMutes(val domain: Domain):
    AtProtocolGet<GetListMutes.GetListMutesRequest, GetListMutes.GetListMutesResponse>(
    action = BskyActions.GetListMutes,
    domain = domain,
    GetListMutesRequest::class,
    GetListMutesResponse::class
) {
    @Serializable
    data class GetListMutesRequest(
        override val accessJwt: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetListMutesResponse(
        val cursor: String = "",
        val lists: List<ListView>
    ): AtProtocolModel
}