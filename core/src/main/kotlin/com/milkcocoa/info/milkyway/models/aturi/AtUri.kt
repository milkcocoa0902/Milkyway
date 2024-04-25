package com.milkcocoa.info.milkyway.models.aturi

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = AtUri.Companion.AtUriSerializer::class)
data class AtUri(
    val uri: String
) {
    companion object {
        @Suppress("ktlint:standard:max-line-length")
        private val didRegexPattern = "did:[a-z]+:[a-zA-Z0-9._:%-]*[a-zA-Z0-9._-]"

        @Suppress("ktlint:standard:max-line-length")
        private val handleRegexPattern = "([a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?"

        @Suppress("ktlint:standard:max-line-length")
        private val NSIDRegex = "[a-zA-Z]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(\\.[a-zA-Z0-9]([a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+(\\.[a-zA-Z]([a-zA-Z]{0,61}[a-zA-Z])?)"

        @Suppress("ktlint:standard:max-line-length")
        private val RecordKeyRegex = "[A-Za-z0-9.-_:~]{1,512}"

        val AtUriRegex = Regex("^at://(($didRegexPattern)|($handleRegexPattern))(/$NSIDRegex(/$RecordKeyRegex)?)?$")

        object AtUriSerializer : KSerializer<AtUri> {
            override val descriptor: SerialDescriptor
                get() = PrimitiveSerialDescriptor("ATUri", PrimitiveKind.STRING)

            override fun deserialize(decoder: Decoder): AtUri {
                return AtUri(decoder.decodeString())
            }

            override fun serialize(
                encoder: Encoder,
                value: AtUri
            ) {
                encoder.encodeString(value.uri)
            }
        }
    }

    val did: String by lazy {
        uri.substringAfter("at://")
            .split("/")
            .firstOrNull()?.let {
                didRegexPattern.toRegex().find(it)?.groups?.firstOrNull()?.value
            } ?: ""
    }
    val handle: String by lazy {
        uri.substringAfter("at://")
            .split("/")
            .firstOrNull()?.let {
                handleRegexPattern.toRegex().find(it)?.groups?.firstOrNull()?.value
            } ?: ""
    }
    val collection: String by lazy {
        uri.substringAfter("at://")
            .split("/")
            .getOrNull(1)?.let {
                NSIDRegex.toRegex().find(it)?.groups?.firstOrNull()?.value ?: ""
            } ?: ""
    }
    val rkey: String by lazy {
        uri.substringAfter("at://")
            .split("/")
            .getOrNull(2)?.let {
                RecordKeyRegex.toRegex().find(it)?.groups?.firstOrNull()?.value ?: ""
            } ?: ""
    }

    init {
        AtUriRegex.matchEntire(uri) ?: error("")
    }
}