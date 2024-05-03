package com.milkcocoa.info.milkyway.models.bsky.feed.threadgate

import com.milkcocoa.info.milkyway.types.GateRuleType
import kotlinx.serialization.Serializable

@Serializable
class ListRule(
    val list: List<String>
) : GateRule() {
    override val type: GateRuleType
        get() = GateRuleType.ListRule
}