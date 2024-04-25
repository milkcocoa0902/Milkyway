package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = RecordType.Companion.Serializer::class)
enum class RecordType(override val identifier: String) : SerializableEnum {
    FeedPostRecord("app.bsky.feed.post"),
    ThreadGateRecord("app.bsky.feed.threadgate"),
    LikeRecord("app.bsky.feed.like"),
    RepostRecord("app.bsky.feed.repost"),
    GeneratorRecord("app.bsky.feed.generator"),
    FollowRecord("app.bsky.graph.follow"),
    BlockRecord("app.bsky.graph.block"),
    ListRecord("app.bsky.graph.list"),
    ListBlockRecord("app.bsky.graph.list.block"),
    ListItemRecord("app.bsky.graph.list.item"),
    ProfileRecord("app.bsky.actor.profile"),
    LabelerServiceRecord("app.bsky.labeler.service"),
    UnknownRecord("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: UnknownRecord

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<RecordType>(RecordType::class)
    }
}