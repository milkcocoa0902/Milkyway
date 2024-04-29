package com.milkcocoa.info.milkyway.api

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.bsky.actor.Actor
import com.milkcocoa.info.milkyway.api.bsky.feed.Feed
import com.milkcocoa.info.milkyway.api.bsky.graph.Graph
import com.milkcocoa.info.milkyway.api.bsky.labeler.Labeler
import com.milkcocoa.info.milkyway.api.bsky.notification.Notification
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.bsky.embed.Embed
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.util.KtorHttpClient

class Bsky(private val domain: Domain){
    fun feed() = Feed(domain)

    fun actor() = Actor(domain)

    fun graph() = Graph(domain)

    fun labeler() = Labeler(domain)

    fun notification() = Notification(domain)
}

fun Milkyway.bsky() = Bsky(domain)