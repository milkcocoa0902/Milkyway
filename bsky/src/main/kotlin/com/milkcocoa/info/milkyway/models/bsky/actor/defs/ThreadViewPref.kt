package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ThreadViewPref(
    val sort: ThreadSortBy? = null,
    val prioritizeFollowedUsers: Boolean? = null
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.ThreadViewPref

    @Serializable
    enum class ThreadSortBy {
        @SerialName("oldest")
        SortedByOldest,

        @SerialName("newest")
        SortedByNewest,

        @SerialName("most-likes")
        SortedByMostLikes,

        @SerialName("random")
        SortedByRandom
    }
}