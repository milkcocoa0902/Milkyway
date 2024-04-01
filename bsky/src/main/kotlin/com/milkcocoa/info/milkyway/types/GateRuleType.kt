package com.milkcocoa.info.milkyway.types

import com.milkcocoa.info.milkyway.util.SerializableEnum
import kotlinx.serialization.Serializable

@Serializable(with = GateRuleType.Companion.Serializer::class)
enum class GateRuleType(override val identifier: String) : SerializableEnum{
    MentionRule("app.bsky.feed.threadgate#mentionRule"),
    FollowingRule("app.bsky.feed.threadgate#followingRule"),
    UnknownRule("unknown");

    companion object{
        fun getByIdentifier(identifier: String?) =
            entries.find { it.identifier == identifier } ?: UnknownRule
        object Serializer: SerializableEnum.Companion.SerializableEnumSerializer<GateRuleType>(GateRuleType::class)
    }
}

