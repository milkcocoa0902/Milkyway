package com.milkcocoa.info.milkyway.models.bsky.textdecor

import com.milkcocoa.info.milkyway.types.RichTextType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = RichTextFeature.Companion::class)
abstract class RichTextFeature {
    @SerialName("\$type")
    abstract val type: RichTextType

    companion object : JsonContentPolymorphicSerializer<RichTextFeature>(RichTextFeature::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<RichTextFeature> {
            return when (RichTextType.getByIdentifier(element.type)) {
                RichTextType.RichTextTypeLink -> RichTextLink.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : RichTextFeature() {
            override var type = RichTextType.Unknown
        }
    }
}