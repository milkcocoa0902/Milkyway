package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.EmbedRecordViewType
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = RecordEmbedViewRecord.Companion::class)
abstract class RecordEmbedViewRecord {

    @SerialName("\$type")
    abstract val type: EmbedRecordViewType

    companion object : JsonContentPolymorphicSerializer<RecordEmbedViewRecord>(RecordEmbedViewRecord::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<RecordEmbedViewRecord> {
            return when (EmbedRecordViewType.getByIdentifier(element.jsonObject["\$type"]?.jsonPrimitive?.contentOrNull)) {
                EmbedRecordViewType.ViewRecord -> RecordEmbedViewViewRecord.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : RecordEmbedViewRecord() {
            override var type = EmbedRecordViewType.UnknownEmbed
        }
    }
}