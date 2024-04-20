package com.milkcocoa.info.milkyway.models.atproto.repo

import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable

@Serializable
data class WrappedRecord(
    val uri: AtUri,
    val cid: String,
    val value: Record<*>
)
