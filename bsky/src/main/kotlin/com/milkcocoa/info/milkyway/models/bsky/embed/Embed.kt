package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.models.bsky.embed.view.ExternalEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.ImageEmbedView
import com.milkcocoa.info.milkyway.models.bsky.embed.view.RecordEmbedView
import com.milkcocoa.info.milkyway.types.EmbedType
import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type

@Serializable(with = Embed.Companion::class)
abstract class Embed{
    @SerialName("\$type")
    abstract val type: EmbedType


    companion object : JsonContentPolymorphicSerializer<Embed>(Embed::class){
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Embed> {
            return when(EmbedType.getByIdentifier(element.type)){
                EmbedType.EmbedImages -> ImageEmbed.serializer()
                EmbedType.EmbedExternal -> ExternalEmbed.serializer()
                EmbedType.EmbedRecord -> RecordEmbed.serializer()
                EmbedType.EmbedRecordWithMedia -> RecordWithMediaEmbed.serializer()
                else -> Unknown.serializer()
            }
        }
        @Serializable
        class Unknown : Embed() {
            override var type = EmbedType.UnknownEmbed
        }
    }
}

