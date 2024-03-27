package com.milkcocoa.info.milkyway.atproto.models.com.atproto.repo

import com.milkcocoa.info.milkyway.atproto.models.AtProtocolRequest
import kotlinx.serialization.Serializable

@Serializable
data class CreateRecordRequest(
    val repo: String,
    val collection: String,
    val record: Record
): AtProtocolRequest {
    @Serializable
    data class Record(
        val text: String,
        val createdAt: String
    )
}