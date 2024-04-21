package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequestWithSession
import com.milkcocoa.info.milkyway.models.atproto.repo.RecordBlob
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Returns a list of missing blobs for the requesting account. Intended to be used in the account migration flow.
 */
class ListMissingBlobs(val domain: Domain) :
    AtProtocolGet<ListMissingBlobs.ListMissingBlobsRequest, ListMissingBlobs.ListMissingBlobsResponse>(
        AtProtoActions.ListMissingBlobs,
        domain,
        ListMissingBlobsRequest::class,
        ListMissingBlobsResponse::class
    ) {
    @Serializable
    data class ListMissingBlobsRequest(
        override val accessJwt: String,
        val limit: Int = 500,
        val cursor: String? = null
    ) : AtProtocolRequestWithSession

    @Serializable
    data class ListMissingBlobsResponse(
        val cursor: String? = null,
        val blobs: List<RecordBlob>
    ) : AtProtocolModel
}