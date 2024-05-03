package com.milkcocoa.info.milkyway.models.bsky.feed.threadgate

import com.milkcocoa.info.milkyway.types.GateRuleType
import kotlinx.serialization.Serializable

@Serializable
class MentionRule : GateRule() {
    override val type: GateRuleType
        get() = GateRuleType.MentionRule
}