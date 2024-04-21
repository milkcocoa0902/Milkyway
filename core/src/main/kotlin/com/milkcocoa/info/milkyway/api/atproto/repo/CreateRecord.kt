package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import kotlinx.serialization.Serializable

/**
 * Create a single new repository record. Requires auth, implemented by PDS.
 */
class CreateRecord(val domain: Domain) :
    AtProtocolPost<CreateRecord.CreateRecordRequest, CreateRecord.CreateRecordResponse>(
        AtProtoActions.CreateRecord,
        domain,
        CreateRecordRequest::class,
        CreateRecordResponse::class
    ) {
    @Serializable
    data class CreateRecordRequest(
        override val accessJwt: String,
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
        val rkey: String? = null,
        val record: Record<*>,
        /**
         * Can be set to 'false' to skip Lexicon schema validation of record data.
         */
        val validate: Boolean = true,
        /**
         * Compare and swap with the previous commit by CID.
         */
        val swapCommit: String? = null
    ) : AtProtocolRequestWithSession

    @Serializable
    data class CreateRecordResponse(
        val uri: AtUri,
        val cid: String
    ) : AtProtocolModel
}