package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolPostRequestModel
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.aturi.ATIdentifier
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.aturi.NSID
import com.milkcocoa.info.milkyway.models.aturi.RecordKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        @Transient
        override val accessJwt: String = "",
        /**
         * The handle or DID of the repo (aka, current account).
         */
        val repo: ATIdentifier,
        /**
         * The NSID of the record collection.
         */
        val collection: NSID,
        /**
         * The Record Key.
         */
        val rkey: RecordKey? = null,
        val record: Record<*>,
        /**
         * Can be set to 'false' to skip Lexicon schema validation of record data.
         */
        val validate: Boolean = true,
        /**
         * Compare and swap with the previous commit by CID.
         */
        val swapCommit: String? = null
    ) : RequireUserSession, AtProtocolPostRequestModel

    @Serializable
    data class CreateRecordResponse(
        val uri: AtUri,
        val cid: String
    ) : AtProtocolModel
}