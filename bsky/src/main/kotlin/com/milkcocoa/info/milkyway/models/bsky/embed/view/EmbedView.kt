package com.milkcocoa.info.milkyway.models.bsky.embed.view

import com.milkcocoa.info.milkyway.types.EmbedViewType
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type

@Serializable(with = EmbedView.Companion::class)
abstract class EmbedView{
    @SerialName("\$type")
    abstract val type: EmbedViewType


    companion object : JsonContentPolymorphicSerializer<EmbedView>(EmbedView::class){
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<EmbedView> {
            return when(EmbedViewType.getByIdentifier(element.type)){
                EmbedViewType.EmbedExternalView -> ExternalEmbedView.serializer()
                EmbedViewType.EmbedImagesView -> ImageEmbedView.serializer()
                EmbedViewType.EmbedRecordView -> RecordEmbedView.serializer()
                else -> Unknown.serializer()
            }
        }
        @Serializable
        class Unknown : EmbedView() {
            override var type = EmbedViewType.UnknownEmbed
        }
    }
}

