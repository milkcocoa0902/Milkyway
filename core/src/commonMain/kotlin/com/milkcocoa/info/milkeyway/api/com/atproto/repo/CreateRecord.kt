package com.milkcocoa.info.milkeyway.api.com.atproto.repo

import com.milkcocoa.info.milkeyway.api.com.atproto.server.CreateSessionRequest
import com.milkcocoa.info.milkeyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkeyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkeyway.atproto.models.AtProtocolRequest
import com.milkcocoa.info.milkeyway.atproto.models.CreateRecord
import com.milkcocoa.info.milkeyway.atproto.models.Session
import com.milkcocoa.info.milkeyway.domain.Domain
import kotlinx.serialization.Serializable

class CreateRecord(val domain: Domain): AtProtocolPost<CreateRecordRequest, CreateRecord>(
    AtProtoActions.ATPROTO_CREATE_RECORD,
    domain,
    CreateRecordRequest::class,
    CreateRecord::class
)

@Serializable
data class CreateRecordRequest(
    val repo: String,
    val collection: String,
    val record: Record
): AtProtocolRequest{
    @Serializable
    data class Record(
        val text: String,
        val createdAt: String
    )
}