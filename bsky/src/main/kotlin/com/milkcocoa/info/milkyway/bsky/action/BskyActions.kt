package com.milkcocoa.info.milkyway.bsky.action

import com.milkcocoa.info.milkyway.atproto.action.Action

object BskyActions {
    val GetTimeLine = Action("app.bsky.feed.getTimeline")
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
}