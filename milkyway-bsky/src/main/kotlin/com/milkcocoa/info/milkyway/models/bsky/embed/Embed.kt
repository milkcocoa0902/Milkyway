package com.milkcocoa.info.milkyway.models.bsky.embed

import com.milkcocoa.info.milkyway.types.EmbedType
import com.milkcocoa.info.milkyway.util.AtProtoDependencyResolver
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Serializable // (with = Embed.Companion.EmbedDeserializer::class)
abstract class Embed : AtProtoDependencyResolver {
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

        private var installed: Boolean = false
        private val lock by lazy { ReentrantLock() }

        public fun markAsInstalled() {
            if (installed.not()) {
                lock.withLock {
                    if (installed.not()) {
                        installed = true
                    }
                }
            }
        }
    }

    init {
        if (installed.not()) {
            lock.withLock {
                if (installed.not()) {
                    installed = true
                    installDependencies()
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