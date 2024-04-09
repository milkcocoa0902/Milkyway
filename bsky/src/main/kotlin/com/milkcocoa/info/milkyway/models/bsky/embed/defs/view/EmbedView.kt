package com.milkcocoa.info.milkyway.models.bsky.embed.defs.view

import com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord.RecordView
import com.milkcocoa.info.milkyway.models.bsky.embed.defs.viewRecord.RecordWithMediaView
import com.milkcocoa.info.milkyway.models.bsky.feed.defs.GeneratorView
import com.milkcocoa.info.milkyway.types.EmbedViewType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = EmbedView.Companion::class)
abstract class EmbedView {
    @SerialName("\$type")
    abstract val type: EmbedViewType

    companion object : JsonContentPolymorphicSerializer<EmbedView>(EmbedView::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<EmbedView> {
            return when (EmbedViewType.getByIdentifier(element.type)) {
                EmbedViewType.EmbedExternalView -> ExternalView.serializer()
                EmbedViewType.EmbedImagesView -> ImageView.serializer()
                EmbedViewType.EmbedRecordView -> RecordView.serializer()
                EmbedViewType.EmbedRecordWithMediaView -> RecordWithMediaView.serializer()
                EmbedViewType.GeneratorView -> GeneratorView.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : EmbedView() {
            override var type = EmbedViewType.UnknownEmbed
        }
    }
}