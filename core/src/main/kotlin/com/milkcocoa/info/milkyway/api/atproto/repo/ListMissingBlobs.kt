package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolGetRequestModel
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.RequireUserSession
import com.milkcocoa.info.milkyway.models.atproto.repo.RecordBlob
import kotlinx.serialization.Serializable

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
    ) : RequireUserSession, AtProtocolGetRequestModel

    @Serializable
    data class ListMissingBlobsResponse(
        val cursor: String? = null,
        val blobs: List<RecordBlob>
    ) : AtProtocolModel
}