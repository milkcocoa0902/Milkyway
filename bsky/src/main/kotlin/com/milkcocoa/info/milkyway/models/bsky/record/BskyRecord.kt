package com.milkcocoa.info.milkyway.models.bsky.record

import com.milkcocoa.info.milkyway.models.Record
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
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.util.AtProtoDependencyResolver
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Serializable
open class BskyRecord() : Record<RecordType>() {
    override val type: RecordType
        get() = TODO("Not yet implemented")

    companion object {
        fun PolymorphicModuleBuilder<Record<RecordType>>.register() {
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
            defaultDeserializer { UnknownBskyRecord.serializer() }
        }

        val serializerModule
            get() =
                SerializersModule {
                    polymorphic(Record::class) {
                        register()
                    }
                }
    }

    override fun installDependencies() {
        KtorHttpClient.addSerializersModule(serializerModule)
    }
}

@Serializable
class UnknownBskyRecord : BskyRecord() {
    override var type = RecordType.UnknownRecord
}