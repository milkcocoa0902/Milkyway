package com.milkcocoa.info.milkyway.bsky.action

import com.milkcocoa.info.milkyway.api.bsky.feed.GetSuggestedFeeds
import com.milkcocoa.info.milkyway.atproto.action.Action

object BskyActions {
    val GetProfile = Action("app.bsky.actor.getProfile")
    val GetProfiles = Action("app.bsky.actor.getProfiles")
    val GetPreferences = Action("app.bsky.actor.getPreferences")
    val GetSuggestions = Action("app.bsky.actor.getSuggestions")
    val PutPreferences = Action("app.bsky.actor.putPreferences")
    val SearchActorsTypeAhead = Action("app.bsky.actor.searchActorsTypeahead")
    val SearchActors = Action("app.bsky.actor.searchActors")


    val GetActorFeeds = Action("app.bsky.feed.getActorFeeds")
    val GetActorLikes = Action("app.bsky.feed.getActorLikes")
    val GetAuthorFeed = Action("app.bsky.feed.getAuthorFeed")
    val GetFeedGenerator = Action("app.bsky.feed.getFeedGenerator")
    val GetFeedGenerators = Action("app.bsky.feed.getFeedGenerators")
    val GetFeed= Action("app.bsky.feed.getFeed")
    val GetLikes= Action("app.bsky.feed.getLikes")
    val GetListFeed= Action("app.bsky.feed.getListFeed")
    val GetPostThread= Action("app.bsky.feed.getPostThread")
    val GetPosts= Action("app.bsky.feed.getPosts")
    val GetRepostedBy= Action("app.bsky.feed.getRepostedBy")
    val GetSuggestedFeeds= Action("app.bsky.feed.getSuggestedFeeds")
    val GetTimeLine = Action("app.bsky.feed.getTimeline")
    val SearchPosts= Action("app.bsky.feed.searchPosts")


    val GetBlocks= Action("app.bsky.graph.getBlocks")
    val GetFollowers = Action("app.bsky.graph.getFollowers")
    val GetFollows = Action("app.bsky.graph.getFollows")
    val GetListBlocks = Action("app.bsky.graph.getListBlocks")
    val GetListMutes = Action("app.bsky.graph.getListMutes")
    val GetList = Action("app.bsky.graph.getList")
    val GetLists = Action("app.bsky.graph.getLists")
    val GetMutes = Action("app.bsky.graph.getMutes")
    val GetSuggestedFollowsByActor = Action("app.bsky.graph.getSuggestedFollowsByActor")
    val GetRelationships = Action("app.bsky.graph.getRelationships")
    val MuteActor = Action("app.bsky.graph.muteActor")
    val MuteActorList = Action("app.bsky.graph.muteActorList")
    val UnMuteActorList = Action("app.bsky.graph.unmuteActorList")
    val UnMuteActor = Action("app.bsky.graph.unmuteActor")

}