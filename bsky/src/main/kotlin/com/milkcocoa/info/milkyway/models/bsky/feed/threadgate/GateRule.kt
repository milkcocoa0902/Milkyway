package com.milkcocoa.info.milkyway.models.bsky.feed.threadgate

import com.milkcocoa.info.milkyway.models.bsky.embed.view.ExternalEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.ImageEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.RecordEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.RecordWithMediaEmbedView
import com.milkcocoa.info.milkyway.types.EmbedViewType
import com.milkcocoa.info.milkyway.types.GateRuleType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = GateRule.Companion::class)
abstract class GateRule {
    @SerialName("\$type")
    abstract val type: GateRuleType


    companion object : JsonContentPolymorphicSerializer<GateRule>(GateRule::class){
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<GateRule> {
            return when(GateRuleType.getByIdentifier(element.type)){
                GateRuleType.MentionRule -> MentionRule.serializer()
                GateRuleType.FollowingRule -> FollowingRule.serializer()
                else -> Unknown.serializer()
            }
        }
        @Serializable
        class Unknown : GateRule() {
            override var type = GateRuleType.UnknownRule
        }
    }
}