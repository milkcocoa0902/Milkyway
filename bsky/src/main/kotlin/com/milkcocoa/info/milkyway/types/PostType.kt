package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = PostType.Companion.Serializer::class)
enum class PostType(override val identifier: String) : SerializableEnum {
    Post("app.bsky.feed.defs#postView"),
    NotFoundPost("app.bsky.feed.defs#notFoundPost"),
    BlockedPost("app.bsky.feed.defs#blockedPost"),
    Unknown("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<PostType>(PostType::class)
    }
}