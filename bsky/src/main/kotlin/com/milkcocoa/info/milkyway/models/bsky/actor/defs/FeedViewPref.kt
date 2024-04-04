package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import com.milkcocoa.info.milkyway.types.ActorPreferenceType
import kotlinx.serialization.Serializable

@Serializable
data class FeedViewPref(
    val feed: String,
    val hideReplies: Boolean? = null,
    val hideRepliesByUnfollowed: Boolean? = null,
    val hideRepliesByLikeCount: Int? = null,
    val hideReposts: Boolean? = null,
    val hideQuotedPosts: Boolean? = null
) : ActorPreferenceDef() {
    override val type: ActorPreferenceType
        get() = ActorPreferenceType.FeedViewPref
}