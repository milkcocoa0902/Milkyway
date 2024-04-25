package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.EmbedType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
abstract class Embed {
    @SerialName("\$type")
    abstract val type: EmbedType

    companion object {
        // change from DeserializationStrategy because ATProtocol server needs a $type field
        val serializerModule get() =
            SerializersModule {
                polymorphic(baseClass = Embed::class) {
                    subclass(ImageEmbed::class)
                    subclass(ExternalEmbed::class)
                    subclass(RecordEmbed::class)
                    subclass(RecordWithMediaEmbed::class)
                    defaultDeserializer { Unknown.serializer() }
                }
            }
    }
}

@Serializable
class Unknown : Embed() {
    override var type = EmbedType.UnknownEmbed
}