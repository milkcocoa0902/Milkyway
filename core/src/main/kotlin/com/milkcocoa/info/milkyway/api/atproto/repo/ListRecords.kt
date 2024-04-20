package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.atproto.repo.RecordBlob
import com.milkcocoa.info.milkyway.models.atproto.repo.WrappedRecord
import kotlinx.serialization.Serializable

/**
 * List a range of records in a repository, matching a specific collection. Does not require auth.
 */
class ListRecords(val domain: Domain) :
    AtProtocolGet<ListRecords.ListRecordsRequest, ListRecords.ListRecordsResponse>(
        AtProtoActions.ListRecords,
        domain,
        ListRecordsRequest::class,
        ListRecordsResponse::class
    ) {
    @Serializable
    data class ListRecordsRequest(
        /**
         * The handle or DID of the repo.
         */
        val repo: String,
        /**
         * The NSID of the record type.
         */
        val collection: String,
        /**
         * The number of records to return.
         */
        val limit: Int = 50,
        /**
         *
         */
        val cursor: String? = null,
        val reverse: Boolean? = null,
    ) : AtProtocolRequest

    @Serializable
    data class ListRecordsResponse(
        val cursor: String? = null,
        val records: List<WrappedRecord>
    ) : AtProtocolModel
}