package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListItemView
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListView
import kotlinx.serialization.Serializable

class GetList(val domain: Domain) :
    AtProtocolGet<GetList.GetListRequest, GetList.GetListResponse>(
        action = BskyActions.GetList,
        domain = domain,
        GetListRequest::class,
        GetListResponse::class
    ) {
    @Serializable
    data class GetListRequest(
        override val accessJwt: String,
        val list: String,
        val limit: Int = 50,
        val cursor: String = ""
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetListResponse(
        val cursor: String = "",
        val list: ListView,
        val items: List<ListItemView>
    ) : AtProtocolModel
}