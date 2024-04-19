package com.milkcocoa.info.milkyway.models.bsky.notification

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class NotificationReason {
    @SerialName("like")
    Like,

    @SerialName("repost")
    Repost,

    @SerialName("follow")
    Follow,

    @SerialName("mention")
    Mention,

    @SerialName("reply")
    Reply,

    @SerialName("quote")
    Quote
}