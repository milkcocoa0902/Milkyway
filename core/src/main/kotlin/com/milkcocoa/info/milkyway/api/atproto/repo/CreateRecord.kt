package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import kotlinx.serialization.Serializable

class CreateRecord(val domain: Domain) :
    AtProtocolPost<CreateRecord.CreateRecordRequest, CreateRecord.CreateRecordResponse>(
        AtProtoActions.ATPROTO_CREATE_RECORD,
        domain,
        CreateRecordRequest::class,
        CreateRecordResponse::class
    ) {
    @Serializable
    data class CreateRecordRequest(
        override val accessJwt: String,
        val repo: String,
        val collection: String,
        val record: Record
    ) : AtProtocolRequestWithSession {
        @Serializable
        data class Record(
            val text: String,
            val createdAt: String
        )
    }

    @Serializable
    data class CreateRecordResponse(
        val uri: String,
        val cid: String
    ) : AtProtocolModel
}