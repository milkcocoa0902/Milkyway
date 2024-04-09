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
}