package com.milkcocoa.info.milkyway.api.bsky.feed

import com.milkcocoa.info.milkyway.domain.Domain

class Feed(
    private val domain: Domain
) {
    suspend fun getActorFeeds(request: GetActorFeeds.GetActorFeedsRequest) = GetActorFeeds(domain).execute(request)

    suspend fun getActorLikes(request: GetActorLikes.GetActorLikesRequest) = GetActorLikes(domain).execute(request)

    suspend fun getAuthorFeed(request: GetAuthorFeed.GetAuthorFeedRequest) = GetAuthorFeed(domain).execute(request)

    suspend fun getFeedGenerator(request: GetFeedGenerator.GetFeedGeneratorRequest) =
        GetFeedGenerator(domain).execute(request)

    suspend fun getFeedGenerators(request: GetFeedGenerators.GetFeedGeneratorsRequest) =
        GetFeedGenerators(domain).execute(request)

    suspend fun getFeed(request: GetFeed.GetFeedRequest) = GetFeed(domain).execute(request)

    suspend fun getLikes(request: GetLikes.GetLikesRequest) = GetLikes(domain).execute(request)

    suspend fun getListFeed(request: GetListFeed.GetListFeedRequest) = GetListFeed(domain).execute(request)

    suspend fun getPostThread(request: GetPostThread.GetPostThreadRequest) = GetPostThread(domain).execute(request)

    suspend fun getPosts(request: GetPosts.GetPostsRequest) = GetPosts(domain).execute(request)

    suspend fun getRepostedBy(request: GetRepostedBy.GetRepostedByRequest) = GetRepostedBy(domain).execute(request)

    suspend fun getSuggestedFeeds(request: GetSuggestedFeeds.GetSuggestedFeedsRequest) =
        GetSuggestedFeeds(domain).execute(request)

    suspend fun getTimeline(request: GetTimeLine.GetTimelineRequest) = GetTimeLine(domain).execute(request)

    suspend fun searchPosts(request: SearchPosts.SearchPostsRequest) = SearchPosts(domain).execute(request)
}