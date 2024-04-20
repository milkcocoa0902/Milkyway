package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolGet
import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.AtProtocolModel
import com.milkcocoa.info.milkyway.models.AtProtocolRequest
import com.milkcocoa.info.milkyway.models.aturi.AtUri
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.models.did.DidDoc
import kotlinx.serialization.Serializable

/**
 * Get information about an account and repository, including the list of collections. Does not require auth.
 */
class DescribeRepo(val domain: Domain) :
    AtProtocolGet<DescribeRepo.DescribeRepoRequest, DescribeRepo.DescribeRepoResponse>(
        AtProtoActions.DescribeRepo,
        domain,
        DescribeRepoRequest::class,
        DescribeRepoResponse::class
    ) {
    @Serializable
    data class DescribeRepoRequest(
        /**
         * The handle or DID of the repo.
         */
        val repo: String,
    ) : AtProtocolRequest

    @Serializable
    data class DescribeRepoResponse(
        val handle: String,
        val did: String,
        val didDoc: DidDoc,
        val collections: List<String>,
        /**
         * Indicates if handle is currently valid (resolves bi-directionally)
         */
        val handleIsCorrect: Boolean
    ) : AtProtocolModel
}