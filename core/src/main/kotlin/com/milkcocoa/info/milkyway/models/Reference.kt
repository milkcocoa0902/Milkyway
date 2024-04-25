package com.milkcocoa.info.milkyway.models

import com.milkcocoa.info.milkyway.ReferenceType
import com.milkcocoa.info.milkyway.models.Reference.Companion
import com.milkcocoa.info.milkyway.models.atproto.admin.def.RepoBlobRef
import com.milkcocoa.info.milkyway.models.atproto.admin.def.RepoRef
import com.milkcocoa.info.milkyway.models.atproto.repo.StrongRef
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement

@Serializable(with = Reference.Companion::class)
abstract class Reference {
    @SerialName("\$type")
    abstract val type: ReferenceType

    companion object : JsonContentPolymorphicSerializer<Reference>(Reference::class) {
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Reference> {
            return when (ReferenceType.getByIdentifier(element.type)) {
                ReferenceType.RepoRef -> RepoRef.serializer()
                ReferenceType.StrongRef -> StrongRef.serializer()
                ReferenceType.RepoBlobRef -> RepoBlobRef.serializer()
                else -> Unknown.serializer()
            }
        }

        @Serializable
        class Unknown(override val type: ReferenceType = ReferenceType.Unknown) : Reference()
    }
}