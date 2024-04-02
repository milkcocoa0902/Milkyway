package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class ThreadViewPref(
    val sort: ThreadSortBy? = null,
    val prioritizeFollowedUsers: Boolean? = null
): ActorPreferenceDef(){
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.ThreadViewPref


    @Serializable(with = ThreadSortBy.Companion.Serializer::class)
    enum class ThreadSortBy(val identifier: String) {
        SortedByOldest("oldest"),
        SortedByNewest("newest"),
        SortedByMostLikes("most-likes"),
        SortedByRandom("random"),
        Unknown("unknown");

        companion object{
            fun getByIdentifier(identifier: String?) =
                entries.find { it.identifier == identifier } ?: Unknown

            object Serializer: KSerializer<ThreadSortBy> {
                override val descriptor: SerialDescriptor get() = PrimitiveSerialDescriptor("sort", PrimitiveKind.STRING)
                override fun deserialize(decoder: Decoder): ThreadSortBy {
                    val value = decoder.decodeString()
                    return ThreadSortBy.entries
                        .firstOrNull { it.identifier == value } ?: throw NoSuchElementException()
                }

                override fun serialize(encoder: Encoder, value: ThreadSortBy) {
                    encoder.encodeString(value.identifier)
                }
            }
        }
    }
}
