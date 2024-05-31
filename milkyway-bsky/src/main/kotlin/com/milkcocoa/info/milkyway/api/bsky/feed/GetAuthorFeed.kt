package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.action.BskyActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolGet
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.FeedViewPost
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class GetAuthorFeed(val domain: Domain) :
    AtProtocolGet<GetAuthorFeed.GetAuthorFeedRequest, GetAuthorFeed.GetAuthorFeedResponse>(
        action = BskyActions.GetAuthorFeed,
        domain = domain,
        GetAuthorFeedRequest::class,
        GetAuthorFeedResponse::class
    ) {
    @Serializable
    data class GetAuthorFeedRequest(
        val actor: ATIdentifier,
        val limit: Int = 50,
        val cursor: String = "",
        val filter: AuthorFeedFilter = AuthorFeedFilter.FilterPostsWithReplies
    ) : AtProtocolRequest, AtProtocolGetRequestModel {
        @Serializable
        enum class AuthorFeedFilter {
            @SerialName("posts_with_replies")
            FilterPostsWithReplies,

            @SerialName("posts_no_replies")
            FilterPostsNoReplies,

            @SerialName("posts_with_media")
            FilterPostsWithMedia
        }
    }

    @Serializable
    data class GetAuthorFeedResponse(
        val cursor: String = "",
        val feeds: List<FeedViewPost>
    ) : AtProtocolModel
}