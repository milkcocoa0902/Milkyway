package com.milkcocoa.info.milkyway.models.atproto.label.defs

import kotlinx.serialization.Serializable

@Serializable
data class Label(
    val ver: Int? = null,
    val src: String,
    val uri: String,
    val cid: String? = null,
    val `val`: String,
    val neg: Boolean? = null,
    val cts: String,
    val exp: String? = null,
    val sig: String? = null
)