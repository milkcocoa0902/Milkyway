package com.milkcocoa.info.milkyway.api

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.bsky.actor.Actor
import com.milkcocoa.info.milkyway.api.bsky.feed.Feed
import com.milkcocoa.info.milkyway.api.bsky.graph.Graph
import com.milkcocoa.info.milkyway.api.bsky.labeler.Labeler
import com.milkcocoa.info.milkyway.api.bsky.notification.Notification
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.models.bsky.record.actor.ProfileRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.FeedPostRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.GeneratorRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.LikeRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.RepostRecord
import com.milkcocoa.info.milkyway.models.bsky.record.feed.ThreadGateRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.BlockRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.FollowRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.ListBlockRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.ListItemRecord
import com.milkcocoa.info.milkyway.models.bsky.record.graph.ListRecord
import com.milkcocoa.info.milkyway.models.bsky.record.labeler.ServiceRecord
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.serializersModuleOf
import kotlinx.serialization.modules.subclass

class Bsky(private val domain: Domain) {
    fun feed() = Feed(domain)

    fun actor() = Actor(domain)

    fun graph() = Graph(domain)

    fun labeler() = Labeler(domain)

    fun notification() = Notification(domain)
}

fun Milkyway.bsky() = Bsky(domain)
fun Milkyway.installBskyDependencies(){
    KtorHttpClient.addSerializersModule(serializersModuleOf(ProfileRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(FeedPostRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(GeneratorRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(LikeRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(RepostRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(ThreadGateRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(BlockRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(FollowRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(FollowRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(ListRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(ListBlockRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(ListItemRecord.serializer()))
    KtorHttpClient.addSerializersModule(serializersModuleOf(ServiceRecord.serializer()))
}