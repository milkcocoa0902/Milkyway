package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.RecordEmbedViewRecordType
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = EmbedView.Companion::class)
abstract class EmbedView{
    @SerialName("\$type")
    abstract val type: RecordEmbedViewRecordType


    companion object : JsonContentPolymorphicSerializer<EmbedView>(EmbedView::class){
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<EmbedView> {
            println(element.jsonObject["\$type"])
            return when(RecordEmbedViewRecordType.getByIdentifier(element.jsonObject["\$type"]?.jsonPrimitive?.contentOrNull)){
                RecordEmbedViewRecordType.EmbedExternalView -> ExternalEmbedView.serializer()
                RecordEmbedViewRecordType.EmbedImagesView -> ImageEmbedView.serializer()
                RecordEmbedViewRecordType.EmbedRecordView -> RecordEmbedView.serializer()
                else -> Unknown.serializer()
            }
        }
        @Serializable
        class Unknown : EmbedView() {
            override var type = RecordEmbedViewRecordType.UnknownEmbed
        }
    }
}

