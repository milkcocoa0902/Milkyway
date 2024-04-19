package com.milkcocoa.info.milkyway.models.bsky.feed.defs

import com.milkcocoa.info.milkyway.models.bsky.actor.ProfileViewBasic
import com.milkcocoa.info.milkyway.types.FeedReasonType
import com.milkcocoa.info.milkyway.util.DateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ReasonRepost(
    val by: ProfileViewBasic,
    @Serializable(with = IndexedAtSerializer::class)
    val indexedAt: LocalDateTime
) : Reason() {
    override val type: FeedReasonType
        get() = FeedReasonType.FeedReasonRepost

    companion object {
        object IndexedAtSerializer : DateTimeSerializer("indexedAt")
    }
}