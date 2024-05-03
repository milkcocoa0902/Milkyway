package com.milkcocoa.info.milkyway.models.bsky.graph

import com.milkcocoa.info.milkyway.models.bsky.graph.defs.NotFoundActor
import com.milkcocoa.info.milkyway.models.bsky.graph.defs.Relationship
import com.milkcocoa.info.milkyway.types.RelationshipType
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable
abstract class Relation {
    @SerialName("\$type")
    abstract val type: RelationshipType

    companion object : JsonContentPolymorphicSerializer<Relation>(Relation::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Relation> {
            return when (RelationshipType.getByIdentifier(element.type)) {
                RelationshipType.NotFoundActor -> NotFoundActor.serializer()
                RelationshipType.Relationship -> Relationship.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown : Relation() {
            override var type = RelationshipType.Unknown
        }
    }
}