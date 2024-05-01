package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.ThreadViewPost
import kotlinx.serialization.Serializable

class GetPostThread(val domain: Domain) :
    AtProtocolGet<GetPostThread.GetPostThreadRequest, GetPostThread.GetPostThreadResponse>(
        action = BskyActions.GetPostThread,
        domain = domain,
        GetPostThreadRequest::class,
        GetPostThreadResponse::class
    ) {
    @Serializable
    data class GetPostThreadRequest(
        override val accessJwt: String,
        val uri: AtUri,
        val depth: Int = 6,
        val parentHeight: Int = 80
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetPostThreadResponse(
        val thread: ThreadViewPost
    ) : AtProtocolModel
}