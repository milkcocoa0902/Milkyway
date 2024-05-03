package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = FeedReasonType.Companion.Serializer::class)
enum class FeedReasonType(override val identifier: String) : SerializableEnum {
    FeedReasonRepost("app.bsky.feed.defs#reasonRepost"),
    Unknown("unknown")
    ;

    companion object {
        fun getByIdentifier(identifier: String?) = entries.find { it.identifier == identifier } ?: Unknown

        object Serializer : SerializableEnum.Companion.SerializableEnumSerializer<FeedReasonType>(FeedReasonType::class)
    }
}