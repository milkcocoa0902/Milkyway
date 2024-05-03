package com.milkcocoa.info.milkyway.models.aturi

import com.milkcocoa.info.milkyway.models.aturi.Did.Companion.DID_REGEX_PATTERN
import com.milkcocoa.info.milkyway.models.aturi.Handle.Companion.HANDLE_REGEX_PATTERN
import com.milkcocoa.info.milkyway.models.aturi.NSID.Companion.NSID_REGEX_PATTERN
import com.milkcocoa.info.milkyway.models.aturi.RecordKey.Companion.RECORD_KEY_REGEX_PATTERN
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
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
        val AtUriRegex =
            Regex(
                "^at://((${Did.DID_REGEX_PATTERN})|(${Handle.HANDLE_REGEX_PATTERN}))(/${NSID.NSID_REGEX_PATTERN}(/${RecordKey.RECORD_KEY_REGEX_PATTERN})?)?$"
            )

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

    @Transient
    val did: Did? by lazy {
        kotlin.runCatching {
            uri.substringAfter("at://")
                .split("/")
                .firstOrNull()?.let {
                    DID_REGEX_PATTERN.toRegex().find(it)?.groups?.firstOrNull()?.value?.let { Did(value = it) }
                }
        }.getOrNull()
    }

    @Transient
    val handle: Handle? by lazy {
        kotlin.runCatching {
            uri.substringAfter("at://")
                .split("/")
                .firstOrNull()?.let {
                    HANDLE_REGEX_PATTERN.toRegex().find(it)?.groups?.firstOrNull()?.value?.let { Handle(value = it) }
                }
        }.getOrNull()
    }

    @Transient
    val collection: NSID? by lazy {
        kotlin.runCatching {
            uri.substringAfter("at://")
                .split("/")
                .getOrNull(1)?.let {
                    NSID_REGEX_PATTERN.toRegex().find(it)?.groups?.firstOrNull()?.value?.let { NSID(value = it) }
                }
        }.getOrNull()
    }

    @Transient
    val rkey: RecordKey? by lazy {
        kotlin.runCatching {
            uri.substringAfter("at://")
                .split("/")
                .getOrNull(2)?.let {
                    RECORD_KEY_REGEX_PATTERN.toRegex().find(
                        it
                    )?.groups?.firstOrNull()?.value?.let { RecordKey(value = it) }
                }
        }.getOrNull()
    }

    init {
        AtUriRegex.matchEntire(uri) ?: error("")
    }
}