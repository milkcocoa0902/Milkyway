package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
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
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.ThreadViewPref

    @Serializable
    enum class ThreadSortBy{
        @SerialName("oldest")
        SortedByOldest,
        @SerialName("newest")
        SortedByNewest,
        @SerialName("most-likes")
        SortedByMostLikes,
        @SerialName("random")
        SortedByRandom
        ;
    }
}