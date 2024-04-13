package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.types.FeedReasonType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = Reason.Companion::class)
abstract class Reason{
    @SerialName("\$type")
    abstract val type: FeedReasonType

    companion object: JsonContentPolymorphicSerializer<Reason>(Reason::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Reason> {
            return when (FeedReasonType.getByIdentifier(element.type)) {
                FeedReasonType.FeedReasonRepost -> ReasonRepost.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : Reason() {
            override var type = FeedReasonType.Unknown
        }
    }
}