package com.milkcocoa.info.milkyway.models.bsky.textdecor

import com.milkcocoa.info.milkyway.models.bsky.feed.FeedPostRecord
import com.milkcocoa.info.milkyway.models.bsky.record.BskyRecord
import com.milkcocoa.info.milkyway.types.RecordType
import com.milkcocoa.info.milkyway.types.RichTextType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable
data class Facet(
    val index: RichTextRange,
    val features: List<RichTextFeature>
)