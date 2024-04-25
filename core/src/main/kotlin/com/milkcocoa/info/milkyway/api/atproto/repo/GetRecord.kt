package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable

/**
 * Get a single record from a repository. Does not require auth.
 */
class GetRecord(val domain: Domain) :
    AtProtocolGet<GetRecord.GetRecordRequest, GetRecord.GetRecordResponse>(
        AtProtoActions.GetRecord,
        domain,
        GetRecordRequest::class,
        GetRecordResponse::class
    ) {
    @Serializable
    data class GetRecordRequest(
        /**
         * The handle or DID of the repo (aka, current account).
         */
        val repo: String,
        /**
         * The NSID of the record collection.
         */
        val collection: String,
        /**
         * The Record Key.
         */
        val rkey: String,
        /**
         * The CID of the version of the record. If not specified, then return the most recent version.
         */
        val cid: String? = null
    ) : AtProtocolRequest

    @Serializable
    data class GetRecordResponse(
        val uri: AtUri,
        val cid: String? = null,
        val value: Record<*>
    ) : AtProtocolModel
}