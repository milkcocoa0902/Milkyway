package com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord

import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.ListView
import com.milkcocoa.info.milkyway.models.bsky.labeler.defs.LabelerView
import com.milkcocoa.info.milkyway.types.EmbedViewRecordType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = RecordViewRecord.Companion::class)
abstract class RecordViewRecord {
    @SerialName("\$type")
    abstract val type: EmbedViewRecordType

    companion object : JsonContentPolymorphicSerializer<RecordViewRecord>(RecordViewRecord::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<RecordViewRecord> {
            return when (EmbedViewRecordType.getByIdentifier(element.type)) {
                EmbedViewRecordType.ViewRecord -> RecordViewViewRecord.serializer()
                EmbedViewRecordType.ViewNotFound -> RecordViewViewNotFound.serializer()
                EmbedViewRecordType.ViewBlocked -> RecordViewViewBlocked.serializer()
                EmbedViewRecordType.ViewGeneratorView -> GeneratorView.GeneratorViewAsViewRecord.serializer()
                EmbedViewRecordType.ViewLabelerView -> LabelerView.LabelerViewAsViewRecord.serializer()
                EmbedViewRecordType.ViewListView -> ListView.ListViewAsRecordViewRecord.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : RecordViewRecord() {
            override var type = EmbedViewRecordType.UnknownEmbed
        }
    }
}