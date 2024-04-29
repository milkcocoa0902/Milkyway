package com.milkcocoa.info.milkyway.models.atproto.moderation.defs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ReasonType {
    @SerialName("com.atproto.moderation.defs.reasonSpam")
    ReasonSpam,

    @SerialName("com.atproto.moderation.defs.reasonViolation")
    ReasonViolation,

    @SerialName("com.atproto.moderation.defs.reasonMisleading")
    ReasonMisleading,

    @SerialName("com.atproto.moderation.defs.reasonSexual")
    ReasonSexual,

    @SerialName("com.atproto.moderation.defs.reasonRude")
    ReasonRude,

    @SerialName("com.atproto.moderation.defs.reasonOther")
    ReasonOther,

    @SerialName("com.atproto.moderation.defs.reasonAppeal")
    ReasonAppeal
}