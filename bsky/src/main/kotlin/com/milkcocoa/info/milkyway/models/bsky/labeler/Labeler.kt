package com.milkcocoa.info.milkyway.models.bsky.labeler

import com.milkcocoa.info.milkyway.models.bsky.labeler.defs.LabelerView
import com.milkcocoa.info.milkyway.models.bsky.labeler.defs.LabelerViewDetailed
import com.milkcocoa.info.milkyway.types.LabelerType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = Labeler.Companion::class)
abstract class Labeler {
    @SerialName("\$type")
    abstract val type: LabelerType

    companion object : JsonContentPolymorphicSerializer<Labeler>(Labeler::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Labeler> {
            return when (LabelerType.getByIdentifier(element.type)) {
                LabelerType.LabelerView -> LabelerView.serializer()
                LabelerType.LabelerViewDetailed -> LabelerViewDetailed.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : Labeler() {
            override var type = LabelerType.Unknown
        }
    }
}