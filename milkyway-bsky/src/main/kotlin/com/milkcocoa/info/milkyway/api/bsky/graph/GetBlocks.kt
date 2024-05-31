package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetBlocks(val domain: Domain) :
    AtProtocolGet<GetBlocks.GetBlocksRequest, GetBlocks.GetBlocksResponse>(
        action = BskyActions.GetBlocks,
        domain = domain,
        GetBlocksRequest::class,
        GetBlocksResponse::class
    ) {
    @Serializable
    data class GetBlocksRequest(
        @Transient
        override val accessJwt: String = "",
        val limit: Int = 50,
        val cursor: String = ""
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetBlocksResponse(
        val cursor: String = "",
        val blocks: List<ProfileView>
    ) : AtProtocolModel
}