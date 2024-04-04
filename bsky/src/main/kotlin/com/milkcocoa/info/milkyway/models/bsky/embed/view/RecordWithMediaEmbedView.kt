package com.milkcocoa.info.milkyway.models.bsky.embed.view

import com.milkcocoa.info.milkyway.models.bsky.embed.viewRecord.RecordEmbedViewRecord
import com.milkcocoa.info.milkyway.types.EmbedViewType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable
data class RecordWithMediaEmbedView(
    val record: RecordWrap,
    @Serializable(with = RecordWithMediaEmbedView.Companion::class)
    val media: EmbedView
) : EmbedView() {
    @Serializable
    data class RecordWrap(
        val record: RecordEmbedViewRecord
    )

    override val type: EmbedViewType
        get() = EmbedViewType.EmbedRecordWithMediaView

    companion object : JsonContentPolymorphicSerializer<EmbedView>(EmbedView::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<EmbedView> {
            return when (EmbedViewType.getByIdentifier(element.type)) {
                EmbedViewType.EmbedImagesView -> ImageEmbedView.serializer()
                EmbedViewType.EmbedExternalView -> ExternalEmbedView.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : EmbedView() {
            override var type = EmbedViewType.UnknownEmbed
        }
    }
}