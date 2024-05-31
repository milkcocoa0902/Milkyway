package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
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
        @Transient
        override val accessJwt: String = "",
        val uris: List<AtUri>
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class GetPostsResponse(
        val posts: List<PostView>
    ) : AtProtocolModel
}