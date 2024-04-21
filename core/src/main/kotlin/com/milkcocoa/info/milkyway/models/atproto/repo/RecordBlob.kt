package com.milkcocoa.info.milkyway.models.atproto.repo

import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable

@Serializable
data class RecordBlob(
    val cid: String,
    val recordUri: AtUri
)