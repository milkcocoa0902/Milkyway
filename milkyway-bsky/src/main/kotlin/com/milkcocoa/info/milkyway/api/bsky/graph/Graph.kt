package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.domain.Domain

class Graph(val domain: Domain) {
    suspend fun getBlocks(request: GetBlocks.GetBlocksRequest) = GetBlocks(domain).execute(request)

    suspend fun getFollowers(request: GetFollowers.GetFollowersRequest) = GetFollowers(domain).execute(request)

    suspend fun getFollows(request: GetFollowers.GetFollowersRequest) = GetFollowers(domain).execute(request)

    suspend fun getList(request: GetList.GetListRequest) = GetList(domain).execute(request)

    suspend fun getListBlocks(request: GetListBlocks.GetListBlocksRequest) = GetListBlocks(domain).execute(request)

    suspend fun getListMutes(request: GetListMutes.GetListMutesRequest) = GetListMutes(domain).execute(request)

    suspend fun getLists(request: GetLists.GetListsRequest) = GetLists(domain).execute(request)

    suspend fun getMutes(request: GetMutes.GetMutesRequest) = GetMutes(domain).execute(request)

    suspend fun getRelationships(request: GetRelationships.GetRelationshipsRequest) =
        GetRelationships(domain).execute(request)

    suspend fun getSuggestedFollowsByActor(request: GetSuggestedFollowsByActor.GetSuggestedFollowsByActorRequest) =
        GetSuggestedFollowsByActor(domain).execute(request)

    suspend fun muteActor(request: MuteActor.MuteActorRequest) = MuteActor(domain).execute(request)

    suspend fun muteActorList(request: MuteActorList.MuteActorListRequest) =
        MuteActorList(
            domain
        ).execute(request)

    suspend fun unMuteActor(request: UnMuteActor.UnMuteActorRequest) = UnMuteActor(domain).execute(request)

    suspend fun unMuteActorList(request: UnMuteActorList.UnMuteActorListRequest) =
        UnMuteActorList(domain).execute(request)
}