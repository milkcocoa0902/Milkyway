package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.EmbedType
import com.milkcocoa.info.milkyway.util.AtProtoDependencyResolver
import com.milkcocoa.info.milkyway.util.JsonElementUtil.type
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable(with = Embed.Companion.EmbedDeserializer::class)
abstract class Embed : AtProtoDependencyResolver() {
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

        object EmbedDeserializer : JsonContentPolymorphicSerializer<Embed>(Embed::class) {
            override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Embed> {
                return when (EmbedType.getByIdentifier(element.type)) {
                    EmbedType.EmbedImages -> ImageEmbed.serializer()
                    EmbedType.EmbedExternal -> ExternalEmbed.serializer()
                    EmbedType.EmbedRecord -> RecordEmbed.serializer()
                    EmbedType.EmbedRecordWithMedia -> RecordWithMediaEmbed.serializer()
                    else -> Unknown.serializer()
                }
            }
        }
    }

    override fun installDependencies() {
        KtorHttpClient.addSerializersModule(serializerModule)
    }
}

@Serializable
class Unknown : Embed() {
    override var type = EmbedType.UnknownEmbed
}