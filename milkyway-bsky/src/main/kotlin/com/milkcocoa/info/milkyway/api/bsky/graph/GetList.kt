package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListItemView
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetList(val domain: Domain) :
    AtProtocolGet<GetList.GetListRequest, GetList.GetListResponse>(
        action = BskyActions.GetList,
        domain = domain,
        GetListRequest::class,
        GetListResponse::class
    ) {
    @Serializable
    data class GetListRequest(
        @Transient
        override val accessJwt: String = "",
        val list: AtUri,
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetListResponse(
        val cursor: String = "",
        val list: ListView,
        val items: List<ListItemView>
    ) : AtProtocolModel
}