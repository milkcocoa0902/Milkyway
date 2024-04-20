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

inline fun <reified sub: Record<*>> polymorphic(): SerializersModule {
    return SerializersModule {
        polymorphic(Record::class) {
            subclass(sub::class)
        }
    }
}
fun Milkyway.bsky() = Bsky(domain)
fun Milkyway.installBskyDependencies(){
    KtorHttpClient.addSerializersModule(polymorphic<ProfileRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<FeedPostRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<GeneratorRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<LikeRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<RepostRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<ThreadGateRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<BlockRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<FollowRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<FollowRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<ListRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<ListBlockRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<ListItemRecord>())
    KtorHttpClient.addSerializersModule(polymorphic<ServiceRecord>())
}