package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.bsky.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.PostView
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

class GetPosts(val domain: Domain) :
    AtProtocolGet<GetPosts.GetPostsRequest, GetPosts.GetPostsResponse>(
        action = BskyActions.GetPosts,
        domain = domain,
        GetPostsRequest::class,
        GetPostsResponse::class
    ) {
    @Serializable
    data class GetPostsRequest(
        override val accessJwt: String,
        val uris: List<String>
    ) : AtProtocolRequestWithSession

    @Serializable
    data class GetPostsResponse(
        val feeds: List<PostView>
    ) : AtProtocolModel
}