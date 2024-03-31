package com.milkcocoa.info.milkyway.models.bsky.embed.viewRecord

import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
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
    abstract val type: EmbedViewRecordType

    companion object : JsonContentPolymorphicSerializer<RecordEmbedViewRecord>(RecordEmbedViewRecord::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<RecordEmbedViewRecord> {
            return when (EmbedViewRecordType.getByIdentifier(element.jsonObject["\$type"]?.jsonPrimitive?.contentOrNull)) {
                EmbedViewRecordType.ViewRecord -> RecordEmbedViewViewRecord.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : RecordEmbedViewRecord() {
            override var type = EmbedViewRecordType.UnknownEmbed
        }
    }
}