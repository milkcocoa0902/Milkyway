package com.milkcocoa.info.milkyway.models.atproto.label.defs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LabelValue() {
    @SerialName("!hide")
    NotHide,

    @SerialName("!promote")
    NotPromote,

    @SerialName("!warn")
    NotWarn,

    @SerialName("!no-unauthenticated")
    NotNoUnauthenticated,

    @SerialName("dmca-violation")
    DmcaViolation,

    @SerialName("doxxing")
    Doxxing,

    @SerialName("porn")
    Porn,

    @SerialName("sexual")
    Sexual,

    @SerialName("nudity")
    Nudity,

    @SerialName("nsfl")
    Nsfl,

    @SerialName("gore")
    Gore
}