package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.action.AtProtoActions
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.http.AtProtocolPost
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.aturi.NSID
import com.milkcocoa.info.milkyway.models.aturi.RecordKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Write a repository record, creating or updating it as needed. Requires auth, implemented by PDS.
 */
class PutRecord(val domain: Domain) :
    AtProtocolPost<PutRecord.PutRecordRequest, PutRecord.PutRecordResponse>(
        AtProtoActions.PutRecord,
        domain,
        PutRecordRequest::class,
        PutRecordResponse::class
    ) {
    @Serializable
    data class PutRecordRequest(
        @Transient
        override val accessJwt: String = "",
        /**
         * The handle or DID of the repo (aka, current account).
         */
        val repo: String,
        /**
         * The NSID of the record collection.
         */
        val collection: NSID,
        /**
         * The Record Key.
         */
        val rkey: RecordKey? = null,
        /**
         * Can be set to 'false' to skip Lexicon schema validation of record data.
         */
        val validate: Boolean = true,
        val record: Record<*>,
        /**
         * Compare and swap with the previous record by CID.
         */
        val swapRecord: String? = null,
        /**
         * Compare and swap with the previous commit by CID.
         */
        val swapCommit: String? = null
    ) : RequireUserSession, AtProtocolPostRequestModel

    @Serializable
    data class PutRecordResponse(
        val uri: AtUri,
        val cid: String
    ) : AtProtocolModel
}