package com.milkcocoa.info.milkyway.models.bsky.actor.defs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MutedWordTarget {
    @SerialName("content")
    Content,

    @SerialName("tag")
    Tag
}