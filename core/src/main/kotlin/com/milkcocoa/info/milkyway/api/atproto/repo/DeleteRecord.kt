package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolUnitPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.NSID
import com.milkcocoa.info.milkyway.models.aturi.RecordKey
import kotlinx.serialization.Serializable

/**
 * Delete a repository record, or ensure it doesn't exist. Requires auth, implemented by PDS.
 */
class DeleteRecord(val domain: Domain) :
    AtProtocolUnitPost<DeleteRecord.DeleteRecordRequest>(
        AtProtoActions.DeleteRecord,
        domain,
        DeleteRecordRequest::class
    ) {
    @Serializable
    data class DeleteRecordRequest(
        override val accessJwt: String,
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
         * Compare and swap with the previous record by CID.
         */
        val swapRecord: String? = null,
        /**
         * Compare and swap with the previous commit by CID.
         */
        val swapCommit: String? = null
    ) : RequireUserSession, AtProtocolPostRequestModel
}