package com.milkcocoa.info.milkyway.api.bsky.graph

import com.milkcocoa.info.milkyway.domain.Domain

class Graph(val domain: Domain) {
    suspend fun getBlocks(request: GetBlocks.GetBlocksRequest) = GetBlocks(domain).execute(request)
    suspend fun getFollowers(request: GetFollowers.GetFollowersRequest) = GetFollowers(domain).execute(request)
    suspend fun getFollows(request: GetFollowers.GetFollowersRequest) = GetFollowers(domain).execute(request)
    suspend fun getListBlocks(request: GetListBlocks.GetListBlocksRequest) = GetListBlocks(domain).execute(request)
}