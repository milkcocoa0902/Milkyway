package com.milkcocoa.info.milkyway.api

import com.milkcocoa.info.milkyway.Milkyway
import com.milkcocoa.info.milkyway.api.bsky.actor.Actor
import com.milkcocoa.info.milkyway.api.bsky.feed.Feed
import com.milkcocoa.info.milkyway.api.bsky.graph.Graph
import com.milkcocoa.info.milkyway.api.bsky.labeler.Labeler
import com.milkcocoa.info.milkyway.api.bsky.notification.Notification
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.bsky.embed.Embed
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
import com.milkcocoa.info.milkyway.util.AtProtoDependencyResolver
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Bsky(private val domain: Domain) : AtProtoDependencyResolver {
    fun feed() = Feed(domain)

    fun actor() = Actor(domain)

    fun graph() = Graph(domain)

    fun labeler() = Labeler(domain)

    fun notification() = Notification(domain)

    companion object {
        val serializerModule
            get() =
                SerializersModule {
                    polymorphic(Record::class) {
                        subclass(ProfileRecord::class)
                        subclass(FeedPostRecord::class)
                        subclass(GeneratorRecord::class)
                        subclass(LikeRecord::class)
                        subclass(RepostRecord::class)
                        subclass(ThreadGateRecord::class)
                        subclass(BlockRecord::class)
                        subclass(FollowRecord::class)
                        subclass(ListRecord::class)
                        subclass(ListBlockRecord::class)
                        subclass(ListItemRecord::class)
                        subclass(ServiceRecord::class)
                    }
                }

        private var installed: Boolean = false
        private val lock by lazy { ReentrantLock() }

        public fun markAsInstalled() {
            if (installed.not()) {
                lock.withLock {
                    if (installed.not()) {
                        installed = true
                    }
                }
            }
        }
    }

    init {
        if (installed.not()) {
            lock.withLock {
                if (installed.not()) {
                    installed = true
                    installDependencies()
                }
            }
        }
    }

    override fun installDependencies() {
        Embed.markAsInstalled()
        KtorHttpClient.addSerializersModule(serializerModule)
        KtorHttpClient.addSerializersModule(Embed.serializerModule)
    }
}

fun Milkyway.bsky() = Bsky(domain)

fun Milkyway.Companion.installBskyDependencies() {
    Bsky.markAsInstalled()
    Embed.markAsInstalled()
    KtorHttpClient.addSerializersModule(Bsky.serializerModule)
    KtorHttpClient.addSerializersModule(Embed.serializerModule)
}